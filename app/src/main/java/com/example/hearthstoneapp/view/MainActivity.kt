package com.example.hearthstoneapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.viewmodel.InfoViewModel
import com.example.hearthstoneapp.viewmodel.InfoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: InfoViewModelFactory
    val infoViewModel: InfoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createObservers()
        infoViewModel.getInfo()
    }

    private fun createObservers() {
        infoViewModel.getState().observe(this) { state ->
            when(state) {
                is InfoViewModel.InfoState.InfoLoaded -> {

                }
                is InfoViewModel.InfoState.ShowError -> {

                }
                InfoViewModel.InfoState.ShowLoading -> {

                }
            }

        }
    }
}