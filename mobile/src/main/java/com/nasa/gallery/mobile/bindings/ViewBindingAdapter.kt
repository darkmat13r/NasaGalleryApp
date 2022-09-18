package com.nasa.gallery.mobile.bindings

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("show")
fun showView(view : View, show : Boolean){
    view.visibility = if( show) View.VISIBLE else View.GONE
}

