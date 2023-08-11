package com.example.hearthstoneapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.hearthstoneapp.databinding.ActivityMainBinding
import com.example.hearthstoneapp.view.adapter.InfoAdapter
import com.example.hearthstoneapp.viewmodel.InfoViewModel
import com.example.hearthstoneapp.viewmodel.InfoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: InfoViewModelFactory
    val infoViewModel: InfoViewModel by viewModels { viewModelFactory }

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createObservers()
        infoViewModel.getInfo()
    }

    private fun createObservers() {
        infoViewModel.getState().observe(this) { state ->
            when(state) {
                is InfoViewModel.InfoState.InfoLoaded -> {
                    binding.cpiLoader.isVisible = false
                    binding.rvInfo.adapter = InfoAdapter(::navigateToSearchCards).apply {
                        updateInfo(state.info)
                    }
                }
                is InfoViewModel.InfoState.ShowError -> {
                    binding.cpiLoader.isVisible = false
                    showAlert(state.message) { infoViewModel.getInfo() }
                }
                InfoViewModel.InfoState.ShowLoading -> {
                    binding.cpiLoader.isVisible = true
                }
            }

        }
    }

    private fun navigateToSearchCards(typeName: String, name: String) {

    }

    fun showAlert(message: String, onOkPressed: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                onOkPressed()
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}