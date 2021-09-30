package com.example.toyawair.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        binding.rvAwair.adapter = awairAdapter
    }

    private fun initViewModel() {
        lifecycle.addObserver(mainViewModel)

        mainViewModel.mainViewStateLiveData.observe(this@MainActivity) { mainViewState ->

            when (mainViewState) {
                is MainViewModel.MainViewState.GetAwairEvents -> {
                    awairAdapter.addAll(mainViewState.events)
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

}