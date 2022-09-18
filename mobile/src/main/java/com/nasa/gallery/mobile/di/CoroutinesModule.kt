package com.nasa.gallery.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {

    @Provides
    @IoDispatcher
    fun providesIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DefaultDispatcher
    fun providesDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Default

    @Provides
    @DefaultDispatcher
    fun providesMainDispatcher() : CoroutineDispatcher = Dispatchers.Main


}