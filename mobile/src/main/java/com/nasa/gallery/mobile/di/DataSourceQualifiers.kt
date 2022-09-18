package com.nasa.gallery.mobile.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LocalSpaceDataSource


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RemoteSpaceDataSource