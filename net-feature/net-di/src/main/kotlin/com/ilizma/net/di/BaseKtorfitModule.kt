package com.ilizma.net.di

import com.ilizma.net.BaseKtorfit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseKtorfitModule {

    @Provides
    @Singleton
    fun provideBaseKtorfit(
        httpClient: HttpClient,
    ): BaseKtorfit = BaseKtorfit(
        httpClient = httpClient,
        baseUrl = BuildConfig.BASE_URL,
    )

}