package com.nasa.mobile.ui.explore

import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.mobile.data.TestData
import okio.IOException

class TestLocalDataSource : SpaceImageDataSource {
    override suspend fun getImages(): List<SpaceImage> {
        return TestData.spaceImages
    }
}

class TestRemoteDataSource : SpaceImageDataSource {
    override suspend fun getImages(): List<SpaceImage> {
        return TestData.spaceImages
    }
}

class TestNetworkErrorRemoteDataSource : SpaceImageDataSource {
    override suspend fun getImages(): List<SpaceImage> {
        throw IOException("Network error occurred")
    }
}