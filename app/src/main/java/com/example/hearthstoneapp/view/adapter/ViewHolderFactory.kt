package com.example.hearthstoneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstoneapp.databinding.ItemInfoOptionBinding
import com.example.hearthstoneapp.view.adapter.DetailedInfoAdapter.*

object ViewHolderFactory {
    fun create(
        parent: ViewGroup,
        viewType: Int,
        callback: (String, String) -> Unit
    ): RecyclerView.ViewHolder {
        val binding =
            ItemInfoOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            1 -> ClassesViewHolder(binding, callback)
            2 -> SetsViewHolder(binding, callback)
            3 -> TypesViewHolder(binding, callback)
            4 -> FactionsViewHolder(binding, callback)
            5 -> QualitiesViewHolder(binding, callback)
            6 -> RacesViewHolder(binding, callback)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
}
