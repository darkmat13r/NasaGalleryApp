package com.nasa.gallery.mobile.di

import android.content.Context
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.nasa.gallery.mobile.data.repository.ResourceSpaceRepository
import com.nasa.gallery.mobile.data.repository.SpaceRepository
import com.nasa.gallery.mobile.data.utils.AssetDataLoader
import com.nasa.gallery.mobile.data.utils.DataLoader
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class DataRepositoryModule {

    @Provides
    @Singleton
    fun providesResourceRepository(dataLoader: DataLoader) : SpaceRepository{
        return ResourceSpaceRepository(dataLoader)
    }

    @Provides
    @Singleton
    fun providesGson() : Gson{
        return Gson()
    }


    @Provides
    @Singleton
    fun providesDataLoader(@ApplicationContext context : Context, gson : Gson) : DataLoader{
        return AssetDataLoader(context, gson)
    }
}