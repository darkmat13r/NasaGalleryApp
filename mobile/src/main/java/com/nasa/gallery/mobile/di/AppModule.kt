package com.nasa.gallery.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun providesApplicationScope(@DefaultDispatcher dispatcher : CoroutineDispatcher ) = CoroutineScope(
        SupervisorJob() + dispatcher )
}