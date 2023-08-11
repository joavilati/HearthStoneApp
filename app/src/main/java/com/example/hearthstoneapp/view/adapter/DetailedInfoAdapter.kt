package com.example.hearthstoneapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstoneapp.databinding.ItemInfoOptionBinding
import com.example.hearthstoneapp.model.*

class DetailedInfoAdapter<T>(
    private val items: List<T>,
    private val callback: (String, String) -> Unit
    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[0]) {
            is Classes -> 1
            is Sets -> 2
            is Types -> 3
            is Factions -> 4
            is Qualities -> 5
            is Races -> 6
            is Locales -> 7
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(parent, viewType, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClassesViewHolder -> holder.bind(items[position] as Classes)
            is SetsViewHolder -> holder.bind(items[position] as Sets)
            is TypesViewHolder -> holder.bind(items[position] as Types)
            is FactionsViewHolder -> holder.bind(items[position] as Factions)
            is QualitiesViewHolder -> holder.bind(items[position] as Qualities)
            is RacesViewHolder -> holder.bind(items[position] as Races)
        }
    }

    override fun getItemCount() = items.size


    abstract class BaseViewHolder<T>(
        protected val binding: ItemInfoOptionBinding,
        private val callback: (String, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
        protected fun createClickButton(typeName: String, name: String){
            binding.btnSearchItem.setOnClickListener { callback(typeName, name) }
        }
    }

    class ClassesViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Classes>(binding, callback) {
        override fun bind(item: Classes) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

    class SetsViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Sets>(binding, callback) {
        override fun bind(item: Sets) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

    class TypesViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Types>(binding, callback) {
        override fun bind(item: Types) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

    class FactionsViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Factions>(binding, callback) {
        override fun bind(item: Factions) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

    class QualitiesViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Qualities>(binding, callback) {
        override fun bind(item: Qualities) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

    class RacesViewHolder(binding: ItemInfoOptionBinding, callback: (String, String) -> Unit) :
        BaseViewHolder<Races>(binding, callback) {
        override fun bind(item: Races) {
            binding.btnSearchItem.text = item.value
            createClickButton(item.typeName, item.value)
        }
    }

}