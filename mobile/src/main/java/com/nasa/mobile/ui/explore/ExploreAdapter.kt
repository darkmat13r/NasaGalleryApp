package com.nasa.mobile.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nasa.gallery.mobile.databinding.ItemExploreBinding

class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ViewHolder>(){
    class ViewHolder(val binding : ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    val images = arrayListOf("https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg", "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg", "https://apod.nasa.gov/apod/image/1912/ElectricMilkyWay_Pedretti_1080.jpg")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageUrl = images.get(position%images.count())
    }
}