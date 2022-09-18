package com.nasa.gallery.mobile.data.source

import com.nasa.gallery.mobile.data.api.NasaService
import com.nasa.gallery.mobile.data.model.SpaceImage
import javax.inject.Inject

class RemoteSpaceImageDataSource @Inject constructor(
   private val nasaService: NasaService
) : SpaceImageDataSource  {
    override fun getImages(): List<SpaceImage> {
        return nasaService.getImages()
    }
}