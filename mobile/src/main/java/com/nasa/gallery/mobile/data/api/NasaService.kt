package com.nasa.gallery.mobile.data.api

import com.nasa.gallery.mobile.data.model.SpaceImage
import retrofit2.http.GET

interface NasaService {

    @GET("api")
    suspend fun getImages() : List<SpaceImage>
}