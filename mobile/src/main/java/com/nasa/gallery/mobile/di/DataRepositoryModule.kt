package com.nasa.gallery.mobile.di

import android.content.Context
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.nasa.gallery.mobile.data.repository.ResourceSpaceRepository
import com.nasa.gallery.mobile.data.repository.SpaceRepository
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
    fun providesResourceRepository(@ApplicationContext context : Context, gson : Gson) : SpaceRepository{
        return ResourceSpaceRepository(context, gson)
    }

    @Provides
    @Singleton
    fun providesGson() : Gson{
        return Gson()
    }
}