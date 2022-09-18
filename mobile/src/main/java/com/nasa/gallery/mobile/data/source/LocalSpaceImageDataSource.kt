package com.nasa.gallery.mobile.data.source

import com.google.gson.reflect.TypeToken
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.utils.DataLoader
import javax.inject.Inject

class LocalSpaceImageDataSource @Inject constructor(
    private val assetDataLoader: DataLoader,
) : SpaceImageDataSource {
    override suspend fun getImages(): List<SpaceImage> {
        return assetDataLoader.read<List<SpaceImage>>(
            "data_source/gallery.json",
            object : TypeToken<List<SpaceImage>>() {}.type
        ) ?: emptyList()
    }
}