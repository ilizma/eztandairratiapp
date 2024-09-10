package com.ilizma.api.data.di

import com.ilizma.api.data.EztandaApi
import com.ilizma.api.data.createEztandaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EztandaApiModule {

    @Provides
    @Singleton
    fun provideEztandaApi(
        ktorfit: Ktorfit,
    ): EztandaApi = ktorfit.createEztandaApi()

}