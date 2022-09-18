package com.nasa.gallery.mobile.di

import android.content.Context
import com.google.gson.Gson
import com.nasa.gallery.mobile.data.source.LocalSpaceImageDataSource
import com.nasa.gallery.mobile.data.source.SpaceImageDataSource
import com.nasa.gallery.mobile.data.utils.AssetDataLoader
import com.nasa.gallery.mobile.data.utils.DataLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [
    SingletonComponent::class
], replaces = [DataRepositoryModule::class])
 class DataRepositoryModuleTest {

    @Provides
    @LocalSpaceDataSource
    fun providesLocalSpaceDataSource(dataLoader: DataLoader): SpaceImageDataSource =
        LocalSpaceImageDataSource(dataLoader)

    @Provides
    @RemoteSpaceDataSource
    fun providesRemoteSpaceDataSource(dataLoader: DataLoader): SpaceImageDataSource =
        LocalSpaceImageDataSource(dataLoader)

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
