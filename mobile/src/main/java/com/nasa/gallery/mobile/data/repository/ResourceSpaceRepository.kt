package com.nasa.gallery.mobile.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nasa.gallery.mobile.data.model.SpaceImage
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class ResourceSpaceRepository @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : SpaceRepository {
    override suspend fun getImages(): List<SpaceImage> {
        val json = context.assets.open("data_source/gallery.json").bufferedReader()
        val type = object : TypeToken<List<SpaceImage>>() {}.rawType
        return gson.fromJson<List<SpaceImage>>(json, type)
    }
}