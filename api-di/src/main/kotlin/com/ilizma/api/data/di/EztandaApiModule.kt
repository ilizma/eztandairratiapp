package com.ilizma.api.data.di

import com.ilizma.api.data.EztandaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object EztandaApiModule {

    @Provides
    fun provideEztandaApi(
        retrofit: Retrofit,
    ): EztandaApi = retrofit.create(EztandaApi::class.java)

}