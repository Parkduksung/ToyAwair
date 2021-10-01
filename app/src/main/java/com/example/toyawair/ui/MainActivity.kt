package com.example.toyawair.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toyawair.R
import com.example.toyawair.databinding.ActivityMainBinding
import com.example.toyawair.ui.adapter.AwairAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val awairAdapter by lazy { AwairAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        setContentView(binding.root)

        with(binding.rvAwair) {
            adapter = awairAdapter
            addOnScrollListener(awairRecyclerViewScrollListener)
        }
    }

    private fun initViewModel() {
        lifecycle.addObserver(mainViewModel)

        mainViewModel.mainViewStateLiveData.observe(this@MainActivity) { mainViewState ->

            when (mainViewState) {
                is MainViewModel.MainViewState.GetAwairEvents -> {
                    awairAdapter.addAll(mainViewState.events)
                    binding.rvAwair.scrollToPosition(INIT_SCROLL_POSITION)
                }

                is MainViewModel.MainViewState.Error -> {
                    Toast.makeText(
                        this@MainActivity,
                        mainViewState.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private val awairRecyclerViewScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisible =
                (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

            val totalItemCount = awairAdapter.itemCount

            if (lastVisible >= totalItemCount - 1) {
                mainViewModel.getEvents()
            }
        }
    }

    companion object {
        private const val INIT_SCROLL_POSITION = 0
    }

}