package com.ilizma.net.di

import com.ilizma.net.BaseRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseRetrofit: BaseRetrofit,
    ): Retrofit = baseRetrofit.retrofit

}