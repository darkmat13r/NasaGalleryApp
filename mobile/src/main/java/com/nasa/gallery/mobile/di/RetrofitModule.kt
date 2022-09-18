package com.nasa.gallery.mobile.di

import android.os.Build
import com.nasa.gallery.mobile.BuildConfig
import com.nasa.gallery.mobile.data.api.Constants
import com.nasa.gallery.mobile.data.api.NasaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {


    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()


    @Singleton
    @Provides
    fun providesNasaServer(retrofit: Retrofit): NasaService {
        return retrofit.create(NasaService::class.java)
    }
}