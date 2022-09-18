package com.nasa.gallery.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@TestInstallIn(components = [
    SingletonComponent::class
], replaces = [CoroutinesModule::class])
@Module
class CoroutinesModuleTest {

    @Provides
    @IoDispatcher
    fun providesIoDispatcher() : CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun providesDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun providesMainDispatcher() : CoroutineDispatcher = Dispatchers.Main


}