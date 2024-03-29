package com.ilizma.net.di

import com.ilizma.net.BaseHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        baseHttpClient: BaseHttpClient
    ): OkHttpClient = baseHttpClient.okHttpClient

}