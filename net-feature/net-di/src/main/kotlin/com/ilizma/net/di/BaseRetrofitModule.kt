package com.ilizma.net.di

import com.ilizma.net.BaseRetrofit
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseRetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): BaseRetrofit = BaseRetrofit(
        okHttpClient,
        moshi,
        BuildConfig.BASE_URL,
    )

}