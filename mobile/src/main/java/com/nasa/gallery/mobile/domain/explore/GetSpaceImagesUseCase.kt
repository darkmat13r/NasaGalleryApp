package com.nasa.gallery.mobile.domain.explore

import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.repository.SpaceRepository
import com.nasa.gallery.mobile.di.IoDispatcher
import com.nasa.gallery.mobile.di.LocalSpaceDataSource
import com.nasa.gallery.mobile.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSpaceImagesUseCase @Inject constructor(
   private val spaceRepository: SpaceRepository,
    @IoDispatcher  dispatcher: CoroutineDispatcher
) : UseCase<List<SpaceImage>>(dispatcher) {
    override suspend fun execute(): List<SpaceImage> {
        return spaceRepository.getImages()
    }


}

