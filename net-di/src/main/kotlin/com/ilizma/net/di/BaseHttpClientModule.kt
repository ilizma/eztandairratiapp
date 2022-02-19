package com.ilizma.net.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.net.BaseHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseHttpClientModule {

    @Provides
    fun provideBaseHttpClient(
        chuckerCollector: ChuckerCollector,
        @ActivityContext context: Context,
    ): BaseHttpClient = BaseHttpClient(
        chuckerCollector,
        context,
        BuildConfig.DEBUG,
    )

}