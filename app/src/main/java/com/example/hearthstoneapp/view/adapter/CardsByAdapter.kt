package com.example.hearthstoneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ItemCardsByBinding
import com.example.hearthstoneapp.model.CardsBy

class CardsByAdapter(private val cardsByList: List<CardsBy>, private val callback: (String) -> Unit) : RecyclerView.Adapter<CardsByAdapter.CardsByViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsByViewHolder {
        val binding = ItemCardsByBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardsByViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardsByViewHolder, position: Int) {
        holder.bind(cardsByList[position])
    }

    override fun getItemCount(): Int = cardsByList.size

    inner class CardsByViewHolder(private val binding: ItemCardsByBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardsBy: CardsBy) {
            binding.tvItemName.text = cardsBy.name
             Glide.with(binding.root.context).load(cardsBy.image).placeholder(R.drawable.card_back).into(binding.ivItem)
            binding.btnItem.setOnClickListener { callback(cardsBy.name) }
        }
    }
}