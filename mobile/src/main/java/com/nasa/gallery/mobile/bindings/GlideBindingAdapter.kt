package com.nasa.gallery.mobile.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy

@BindingAdapter("android:loadFromUrl")
fun loadFromUrl(imageView: ImageView, url : String){
    Glide.with(imageView)
        .load(url)
        .downsample(DownsampleStrategy.AT_LEAST)
        .override(230)
        .into(imageView)
}