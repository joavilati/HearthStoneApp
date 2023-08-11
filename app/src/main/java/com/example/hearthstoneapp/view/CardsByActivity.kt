package com.example.hearthstoneapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ActivityCardsByBinding
import com.example.hearthstoneapp.databinding.ActivityMainBinding
import com.example.hearthstoneapp.viewmodel.CardsByViewModel
import com.example.hearthstoneapp.viewmodel.CardsByViewModelFactory
import com.example.hearthstoneapp.viewmodel.InfoViewModel
import com.example.hearthstoneapp.viewmodel.InfoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CardsByActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: CardsByViewModelFactory

    private val viewModel: CardsByViewModel by viewModels { viewModelFactory }

    companion object {
        private const val TYPE_NAME = "com.example.hearthstoneapp.view.TYPE_NAME"
        private const val NAME = "com.example.hearthstoneapp.view.NAME"

        fun createIntent(context: Context, typeName: String, name: String): Intent {
            return  Intent(context, CardsByActivity::class.java).apply {
                putExtra(TYPE_NAME, typeName)
                putExtra(NAME, name)
            }
        }
    }

    private val typeName: String by lazy {
        intent.getStringExtra(TYPE_NAME)?:""
    }

    private val name: String by lazy {
        intent.getStringExtra(NAME)?:""
    }

    private var _binding: ActivityCardsByBinding? = null
    private val binding: ActivityCardsByBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCardsByBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createObsrever()
        viewModel.getCadsBy(typeName, name)
    }

    private fun createObsrever() {
        viewModel.getState().observe(this) {
            when(it){
                is CardsByViewModel.CardsByState.CardsByLoaded -> {
                    println("Ninja success")
                }
                is CardsByViewModel.CardsByState.ShowError -> println("Ninja error ${it.message}")
                CardsByViewModel.CardsByState.ShowLoading -> println("Ninja success")
            }
        }
    }
}