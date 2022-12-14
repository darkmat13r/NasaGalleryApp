package com.nasa.gallery.mobile.presentation.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.ItemExploreBinding


class ExploreAdapter() : ListAdapter<SpaceImage, ExploreAdapter.ViewHolder>(ExploreAdapter.DiffUtilCallback()){

    lateinit var onSelect : (spaceImage :SpaceImage, imageView : ImageView) -> Unit
    private class DiffUtilCallback : DiffUtil.ItemCallback<SpaceImage>(){
        override fun areItemsTheSame(oldItem: SpaceImage, newItem: SpaceImage): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SpaceImage, newItem: SpaceImage): Boolean {
            return oldItem == newItem
        }

    }
    class ViewHolder(val binding : ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position]?.let { spaceImage ->
            holder.binding.imageUrl = spaceImage.url
                    holder.binding.image.transitionName = spaceImage.url
            holder.binding.image.setOnClickListener {
                if(::onSelect.isInitialized){
                    onSelect(spaceImage, holder.binding.image)
                }
            }
        }

    }

    fun setItems(spaceImages: List<SpaceImage>){
        submitList(spaceImages)
    }
}