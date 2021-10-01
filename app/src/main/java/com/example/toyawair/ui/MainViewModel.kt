package com.example.toyawair.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.toyawair.api.response.AwairEvent
import com.example.toyawair.data.repo.AwairRepository
import com.example.toyawair.utils.MutableSingleLiveData
import com.example.toyawair.utils.Result
import com.example.toyawair.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
    private val awairRepository: AwairRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val _mainViewStataLiveData = MutableSingleLiveData<MainViewState>()
    val mainViewStateLiveData: SingleLiveData<MainViewState> = _mainViewStataLiveData

    private val nextTokenPageQueue: Queue<String> = LinkedList()

    init {
        nextTokenPageQueue.add(INIT_TOKEN)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getEvents() {
        viewModelScope.launch(Dispatchers.Main) {

            when (val result = awairRepository.getEvent(nextTokenPageQueue.last())) {

                is Result.Success -> {
                    _mainViewStataLiveData.setValue(MainViewState.GetAwairEvents(result.data.events.filter { it.title != EMPTY }))
                    nextTokenPageQueue.add(result.data.next_page_token)
                }

                is Result.Error -> {
                    _mainViewStataLiveData.setValue(MainViewState.Error(result.exception.message.orEmpty()))
                }

            }
        }
    }


    companion object {

        private const val INIT_TOKEN = ""
        private const val EMPTY = ""
    }

    sealed class MainViewState {
        data class GetAwairEvents(val events: List<AwairEvent>) : MainViewState()
        data class Error(val errorMessage: String) : MainViewState()
    }
}