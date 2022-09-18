package com.nasa.gallery.mobile.data.source

import com.nasa.gallery.mobile.data.model.SpaceImage

interface SpaceImageDataSource {
    fun getImages() : List<SpaceImage>
}