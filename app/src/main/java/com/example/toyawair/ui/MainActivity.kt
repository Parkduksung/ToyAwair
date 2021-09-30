package com.example.toyawair.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.toyawair.R
import com.example.toyawair.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        setContentView(binding.root)
    }

    private fun initViewModel() {
        lifecycle.addObserver(mainViewModel)

        mainViewModel.mainViewStateLiveData.observe(this@MainActivity) { mainViewState ->

            when (mainViewState) {
                is MainViewModel.MainViewState.GetAwairEvents -> {

                }

                is MainViewModel.MainViewState.Error -> {

                }
            }
        }
    }

}