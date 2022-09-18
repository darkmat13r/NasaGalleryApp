package com.nasa.gallery.mobile.data.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nasa.gallery.mobile.data.model.SpaceImage
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

interface DataLoader {
    fun <T> read(path : String, type : Type) : T?
}

class AssetDataLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson : Gson? = null
    ): DataLoader{

    override fun <T> read(path: String, type : Type) : T?{
        val json = context.assets.open(path).bufferedReader()
        return gson?.fromJson(json, type)
    }
}