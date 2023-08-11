package com.example.hearthstoneapp.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ActivityCardsByBinding
import com.example.hearthstoneapp.databinding.ActivitySingleCardBinding
import com.example.hearthstoneapp.model.Card
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

        fun createIntent(context: Context, name: String): Intent {
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
        configToolbar()
        viewModel.getSingleCard(name)
    }

    private fun configToolbar() {
        binding.ctCard.title = name
    }

    private fun createObservers() {
        viewModel.getState().observe(this) { state ->
            when (state) {
                is SingleCardViewModel.SingleCardState.ShowError -> {
                    binding.loadingBackground.isVisible = false
                    binding.cpiLoader.isVisible = false
                }
                SingleCardViewModel.SingleCardState.ShowLoading -> {
                    binding.loadingBackground.isVisible = true
                    binding.cpiLoader.isVisible = true
                }
                is SingleCardViewModel.SingleCardState.SingleCardLoaded -> {
                    binding.loadingBackground.isVisible = false
                    binding.cpiLoader.isVisible = false
                    configLayout(state.data)
                }
            }
        }
    }

    private fun configLayout(card: Card) {
        Glide.with(this).load(card.image).placeholder(R.drawable.card_back).into(binding.ivCard)

        binding.tvDescription.text = Html.fromHtml(card.description, Html.FROM_HTML_MODE_LEGACY)

        binding.tvAttack.text = getString(R.string.label_attack, card.attack.toString())
        binding.tvName.text = getString(R.string.label_name, card.name)
        binding.tvFlavor.text = getString(R.string.label_flavor, card.flavor)
        binding.tvSet.text = getString(R.string.label_set, card.set)
        binding.tvFaction.text = getString(R.string.label_faction, card.faction)
        binding.tvRarity.text = getString(R.string.label_rarity, card.rarity)
        binding.tvCost.text = getString(R.string.label_cost, card.cost.toString())
        binding.tvHealth.text = getString(R.string.label_health, card.health.toString())
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}