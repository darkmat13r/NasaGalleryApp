package com.nasa.gallery.mobile.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.ItemExploreBinding
import com.nasa.gallery.mobile.databinding.ItemImageDetailBinding
import com.nasa.gallery.mobile.presentation.ui.explore.ExploreAdapter

class DetailAdapter : ListAdapter<SpaceImage, DetailAdapter.ViewHolder>(
    DetailAdapter.DiffUtilCallback()) {
    lateinit var onSelect : (spaceImage : SpaceImage, imageView : ImageView) -> Unit
    private class DiffUtilCallback : DiffUtil.ItemCallback<SpaceImage>(){
        override fun areItemsTheSame(oldItem: SpaceImage, newItem: SpaceImage): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SpaceImage, newItem: SpaceImage): Boolean {
            return oldItem == newItem
        }

    }
    class ViewHolder(val binding : ItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position]?.let{
            holder.binding.spaceImage = it
            holder.binding.image.transitionName = it.url
        }

    }

    fun setItems(spaceImages: List<SpaceImage>){
        submitList(spaceImages)
    }
}