package com.nasa.gallery.mobile.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.nasa.gallery.mobile.R

@BindingAdapter("android:loadFromUrl")
fun loadFromUrl(imageView: ImageView, url : String?){
    Glide.with(imageView)
        .load(url)
        .downsample(DownsampleStrategy.AT_LEAST)
        .placeholder(R.drawable.placeholder)
        .into(imageView)
}