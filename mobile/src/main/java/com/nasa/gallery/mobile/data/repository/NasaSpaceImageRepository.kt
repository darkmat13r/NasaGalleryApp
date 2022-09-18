package com.nasa.gallery.mobile.data.repository

import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.gallery.mobile.di.LocalSpaceDataSource
import com.nasa.gallery.mobile.di.RemoteSpaceDataSource
import okio.IOException
import javax.inject.Inject

class NasaSpaceImageRepository @Inject constructor(
   @LocalSpaceDataSource private val localDataSource: SpaceImageDataSource,
   @RemoteSpaceDataSource private val remoteDataSource: SpaceImageDataSource
) : SpaceRepository {
    override suspend fun getImages(): List<SpaceImage> {
       try{
           return remoteDataSource.getImages()
       }catch(ioEx : IOException){
           return localDataSource.getImages()
       }
    }
}