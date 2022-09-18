package com.nasa.gallery.mobile.di

import android.content.Context
import com.google.gson.Gson
import com.nasa.gallery.mobile.data.api.NasaService
import com.nasa.gallery.mobile.data.repository.NasaSpaceImageRepository
import com.nasa.gallery.mobile.data.repository.SpaceRepository
import com.nasa.gallery.mobile.data.source.LocalSpaceImageDataSource
import com.nasa.gallery.mobile.data.source.RemoteSpaceImageDataSource
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.gallery.mobile.data.utils.AssetDataLoader
import com.nasa.gallery.mobile.data.utils.DataLoader
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class DataRepositoryModule {



    @Provides
    @LocalSpaceDataSource
    fun providesLocalSpaceDataSource(dataLoader: DataLoader): SpaceImageDataSource =
        LocalSpaceImageDataSource(dataLoader)

    @Provides
    @RemoteSpaceDataSource
    fun providesRemoteSpaceDataSource(nasaService: NasaService): SpaceImageDataSource =
        RemoteSpaceImageDataSource(nasaService)

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }


    @Provides
    @Singleton
    fun providesDataLoader(@ApplicationContext context: Context, gson: Gson): DataLoader {
        return AssetDataLoader(context, gson)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryBindings{
    @Binds
    abstract fun bindSpaceRepository(
        spaceImageRepository: NasaSpaceImageRepository
    ): SpaceRepository
}