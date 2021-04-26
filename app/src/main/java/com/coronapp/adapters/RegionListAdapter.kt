package com.coronapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coronapp.databinding.ItemRegionBinding
import com.coronapp.models.Region
import com.squareup.picasso.Picasso
import java.util.*

class RegionListAdapter(private val regions: List<Region>, val onRegionSelected : (region: Region) -> Unit) : RecyclerView.Adapter<RegionListAdapter.RegionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        return RegionViewHolder(ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(regions[position])
    }

    inner class RegionViewHolder(private val binding: ItemRegionBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(region: Region) {
            binding.region = region
            val code = region.iso3166a2.toLowerCase(Locale.ROOT);
            Picasso.get().load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png250px/$code.png").into(binding.imageView)
            binding.root.setOnClickListener { onRegionSelected(region) }
        }

    }

    override fun getItemCount() = regions.size

}