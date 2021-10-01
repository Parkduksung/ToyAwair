package com.example.toyawair.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toyawair.R
import com.example.toyawair.databinding.ActivityMainBinding
import com.example.toyawair.ext.setupActionBar
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

        setupActionBar(R.id.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_refresh)
            setDisplayHomeAsUpEnabled(true)
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

                    when (mainViewState.errorType) {
                        ErrorType.INIT -> {
                            Toast.makeText(
                                this@MainActivity,
                                getString(
                                    R.string.error_message_init,
                                    mainViewState.errorMessage
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ErrorType.SCROLL -> {
                            Toast.makeText(
                                this@MainActivity,
                                getString(
                                    R.string.error_message_scroll,
                                    mainViewState.errorMessage
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            mainViewModel.getEvents()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val INIT_SCROLL_POSITION = 0
    }

}