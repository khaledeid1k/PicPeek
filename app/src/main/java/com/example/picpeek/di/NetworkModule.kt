package com.example.picpeek.di

import com.example.picpeek.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideService(
        gsonConverterFactory: GsonConverterFactory,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(com.example.picpeek.BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideGsonConverterFactory()=GsonConverterFactory.create()
}