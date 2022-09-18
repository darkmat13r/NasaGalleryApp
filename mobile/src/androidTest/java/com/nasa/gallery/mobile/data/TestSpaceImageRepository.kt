package com.nasa.gallery.mobile.data

import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.repository.SpaceRepository
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.mobile.data.TestData
import javax.inject.Inject

class TestSpaceImageRepository @Inject constructor(
    localImageDataSource: SpaceImageDataSource,
    remoteImageDataSource: SpaceImageDataSource
) : SpaceRepository{
    override suspend fun getImages(): List<SpaceImage> {
        return TestData.spaceImages
    }

}