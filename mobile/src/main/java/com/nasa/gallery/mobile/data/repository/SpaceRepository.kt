package com.nasa.gallery.mobile.data.repository

import com.nasa.gallery.mobile.data.model.SpaceImage

interface SpaceRepository {
    suspend fun getImages() : List<SpaceImage>
}