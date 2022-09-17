package com.nasa.gallery.mobile.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.utils.AssetDataLoader
import com.nasa.gallery.mobile.data.utils.DataLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class ResourceSpaceRepository @Inject constructor(
    private val assetDataLoader: DataLoader,
) : SpaceRepository {
    override suspend fun getImages(): List<SpaceImage> {
        return assetDataLoader.read<List<SpaceImage>>(
            "data_source/gallery.json",
            object : TypeToken<List<SpaceImage>>() {}.type
        ) ?: emptyList()
    }
}