package com.example.hearthstoneapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ActivityCardsByBinding
import com.example.hearthstoneapp.databinding.ActivitySingleCardBinding
import com.example.hearthstoneapp.viewmodel.CardsByViewModel
import com.example.hearthstoneapp.viewmodel.CardsByViewModelFactory
import com.example.hearthstoneapp.viewmodel.SingleCardViewModel
import com.example.hearthstoneapp.viewmodel.SingleCardViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleCardActivity : AppCompatActivity() {

    companion object {
        private const val NAME = "com.example.hearthstoneapp.view.SingleCardActivity.NAME"

        fun createIntent(context: Context,name: String): Intent {
            return Intent(context, SingleCardActivity::class.java).apply {
                putExtra(NAME, name)
            }
        }
    }

    private val name: String by lazy {
        intent.getStringExtra(NAME) ?: ""
    }

    @Inject
    lateinit var viewModelFactory: SingleCardViewModelFactory

    private val viewModel: SingleCardViewModel by viewModels { viewModelFactory }

    private var _binding: ActivitySingleCardBinding? = null
    private val binding: ActivitySingleCardBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySingleCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createObservers()
        viewModel.getSingleCard(name)
    }

    private fun createObservers() {
        viewModel.getState().observe(this){ state ->
            when(state){
                is SingleCardViewModel.SingleCardState.ShowError -> {}
                SingleCardViewModel.SingleCardState.ShowLoading -> {}
                is SingleCardViewModel.SingleCardState.SingleCardLoaded -> {}
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}