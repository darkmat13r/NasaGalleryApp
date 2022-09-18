package com.nasa.mobile.ui.explore

import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.mobile.data.TestData

class TestDataSource : SpaceImageDataSource {
    override fun getImages(): List<SpaceImage> {
        return TestData.spaceImages
    }
}