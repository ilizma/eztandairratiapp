package com.ilizma.net

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class OkHttpClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        baseHttpClient: BaseHttpClient
    ): OkHttpClient = baseHttpClient.okHttpClient

}