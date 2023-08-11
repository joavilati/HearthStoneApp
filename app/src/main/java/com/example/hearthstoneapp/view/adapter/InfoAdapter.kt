package com.example.hearthstoneapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.databinding.ItemTypeInfoCardBinding
import com.example.hearthstoneapp.model.*

class InfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val initialTypes = listOf("Classes", "Sets", "Types", "Factions", "Qualities", "Races", "Locales")
    private val infoTypes = mutableListOf<String>().apply { addAll(initialTypes) }
    private var info = Info()

    private val classesList: MutableList<Classes> = mutableListOf()
    private val setsList: MutableList<Sets> = mutableListOf()
    private val typesList: MutableList<Types> = mutableListOf()
    private val factionsList: MutableList<Factions> = mutableListOf()
    private val qualitiesList: MutableList<Qualities> = mutableListOf()
    private val racesList: MutableList<Races> = mutableListOf()
    private val localesList: MutableList<Locales> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTypeInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (infoTypes[viewType]) {
            "Classes" -> ClassesViewHolder(binding)
            "Sets" -> SetsViewHolder(binding)
            "Types" -> TypesViewHolder(binding)
            "Factions" -> FactionsViewHolder(binding)
            "Qualities" -> QualitiesViewHolder(binding)
            "Races" -> RacesViewHolder(binding)
            "Locales" -> LocalesViewHolder(binding)
            else -> throw IllegalArgumentException("Invalid enum name")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClassesViewHolder -> holder.bind(info.classes)
            is SetsViewHolder -> holder.bind(info.sets)
            is TypesViewHolder -> holder.bind(info.types)
            is FactionsViewHolder -> holder.bind(info.factions)
            is QualitiesViewHolder -> holder.bind(info.qualities)
            is RacesViewHolder -> holder.bind(info.races)
            is LocalesViewHolder -> holder.bind(info.locales)
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
        if (info.locales.isEmpty()) infoTypes.remove("Locales")
        notifyDataSetChanged()
    }


    class ClassesViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(classes: List<Classes>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.classes)
        }
    }

    class SetsViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sets: List<Sets>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.sets)
        }
    }

    class TypesViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(types: List<Types>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.types)
        }
    }

    class FactionsViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(factions: List<Factions>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.factions)
        }
    }

    class QualitiesViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(qualities: List<Qualities>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.qualities)
        }
    }

    class RacesViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(races: List<Races>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.races)
        }
    }

    class LocalesViewHolder(private val binding: ItemTypeInfoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(locales: List<Locales>) {
            binding.tvItemName.text = binding.root.context.getString(R.string.locales)
        }
    }

}