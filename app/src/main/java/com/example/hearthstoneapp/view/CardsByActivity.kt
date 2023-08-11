package com.example.hearthstoneapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.hearthstoneapp.databinding.ActivityCardsByBinding
import com.example.hearthstoneapp.util.extensions.showAlert
import com.example.hearthstoneapp.view.adapter.CardsByAdapter
import com.example.hearthstoneapp.viewmodel.CardsByViewModel
import com.example.hearthstoneapp.viewmodel.CardsByViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CardsByActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: CardsByViewModelFactory

    private val viewModel: CardsByViewModel by viewModels { viewModelFactory }

    companion object {
        private const val TYPE_NAME = "com.example.hearthstoneapp.view.CardsByActivity.TYPE_NAME"
        private const val NAME = "com.example.hearthstoneapp.view.CardsByActivity.NAME"

        fun createIntent(context: Context, typeName: String, name: String): Intent {
            return Intent(context, CardsByActivity::class.java).apply {
                putExtra(TYPE_NAME, typeName)
                putExtra(NAME, name)
            }
        }
    }

    private val typeName: String by lazy {
        intent.getStringExtra(TYPE_NAME) ?: ""
    }

    private val name: String by lazy {
        intent.getStringExtra(NAME) ?: ""
    }

    private var _binding: ActivityCardsByBinding? = null
    private val binding: ActivityCardsByBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCardsByBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createObservers()
        viewModel.getCardsBy(typeName, name)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
    private fun createObservers() {
        viewModel.getState().observe(this) { state ->
            when (state) {
                is CardsByViewModel.CardsByState.CardsByLoaded -> {
                    binding.cpiLoader.isVisible = false
                    binding.rvCardsBy.adapter = CardsByAdapter(state.data, ::navigateToSingleCardActivity)
                }
                is CardsByViewModel.CardsByState.ShowError -> {
                    binding.cpiLoader.isVisible = false
                    println("Ninja ${state.message}")
                    showAlert(state.message) { viewModel.getCardsBy(typeName, name) }
                }
                CardsByViewModel.CardsByState.ShowLoading -> {
                    binding.cpiLoader.isVisible = true
                }
            }
        }
    }

    private fun navigateToSingleCardActivity(name: String) {
        startActivity(SingleCardActivity.createIntent(this, name))
    }
}