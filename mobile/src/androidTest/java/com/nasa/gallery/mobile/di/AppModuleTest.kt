package com.nasa.gallery.mobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@TestInstallIn(components = [
    SingletonComponent::class
], replaces = [AppModule::class])
@Module
class AppModuleTest {
    @Provides
    @ApplicationScope
    fun providesApplicationScope(@DefaultDispatcher dispatcher : CoroutineDispatcher ) = CoroutineScope(
        SupervisorJob() + dispatcher )
}