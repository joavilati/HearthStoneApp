package com.example.hearthstoneapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ItemTypeInfoCardBinding
import com.example.hearthstoneapp.model.*

class InfoAdapter(private val callback: (String, String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val initialTypes = listOf("Classes", "Sets", "Types", "Factions", "Qualities", "Races")
    private val infoTypes = mutableListOf<String>().apply { addAll(initialTypes) }
    private var info = Info()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTypeInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (infoTypes[viewType]) {
            "Classes" -> InfoClassesViewHolderInfo(binding, callback)
            "Sets" -> InfoSetsViewHolderInfo(binding, callback)
            "Types" -> InfoTypesViewHolderInfo(binding, callback)
            "Factions" -> InfoFactionsViewHolderInfo(binding, callback)
            "Qualities" -> InfoQualitiesViewHolderInfo(binding, callback)
            "Races" -> InfoRacesViewHolderInfo(binding, callback)
            else -> throw IllegalArgumentException("Invalid enum name")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InfoClassesViewHolderInfo -> holder.bind(info.classes)
            is InfoSetsViewHolderInfo -> holder.bind(info.sets)
            is InfoTypesViewHolderInfo -> holder.bind(info.types)
            is InfoFactionsViewHolderInfo -> holder.bind(info.factions)
            is InfoQualitiesViewHolderInfo -> holder.bind(info.qualities)
            is InfoRacesViewHolderInfo -> holder.bind(info.races)
        }
    }

    override fun getItemCount(): Int = infoTypes.size

    override fun getItemViewType(position: Int): Int = position

    fun updateInfo(info: Info) {
        infoTypes.clear()
        infoTypes.addAll(initialTypes)
        this.info = info
        if (info.classes.isEmpty()) infoTypes.remove("Classes")
        if (info.sets.isEmpty()) infoTypes.remove("Sets")
        if (info.types.isEmpty()) infoTypes.remove("Types")
        if (info.factions.isEmpty()) infoTypes.remove("Factions")
        if (info.qualities.isEmpty()) infoTypes.remove("Qualities")
        if (info.races.isEmpty()) infoTypes.remove("Races")
        notifyDataSetChanged()
    }

    abstract class InfoBaseViewHolder<T>(
        protected val binding: ItemTypeInfoCardBinding,
        private val callback: (String, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        open fun <T> bind(list: List<T>) {
            createAdapter(list)
            setupExpandableFunctionality()
        }

        private fun <T> createAdapter(list: List<T>) {
            binding.rvDetailedInfoList.adapter = DetailedInfoAdapter(list, callback)
        }

        private fun setupExpandableFunctionality() {
            binding.btnExpandableArea.setOnClickListener {
                if (binding.rvDetailedInfoList.visibility == View.GONE) {
                    expandRecyclerView()
                } else {
                    collapseRecyclerView()
                }
            }
        }

        private fun expandRecyclerView() {
            binding.rvDetailedInfoList.visibility = View.VISIBLE
            binding.icDropDown.setImageResource(R.drawable.ic_round_arrow_drop_up_24)
        }

        private fun collapseRecyclerView() {
            binding.rvDetailedInfoList.visibility = View.GONE
            binding.icDropDown.setImageResource(R.drawable.ic_round_arrow_drop_down_24)
        }
    }


    class InfoClassesViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Classes>(binding, callback) {
        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.classes)
        }
    }

    class InfoSetsViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Sets>(binding, callback) {

        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.sets)
        }
    }

    class InfoTypesViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Types>(binding, callback) {
        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.types)
        }
    }

    class InfoFactionsViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Factions>(binding, callback) {
        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.factions)
        }
    }

    class InfoQualitiesViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Qualities>(binding, callback) {
        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.qualities)
        }
    }

    class InfoRacesViewHolderInfo(
        binding: ItemTypeInfoCardBinding,
        callback: (String, String) -> Unit
    ) : InfoBaseViewHolder<Races>(binding, callback) {
        override fun <T> bind(list: List<T>) {
            super.bind(list)
            binding.tvItemName.text = binding.root.context.getString(R.string.races)
        }
    }


}