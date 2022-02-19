package com.ilizma.net.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChuckerCollectorModule {

    @Provides
    fun provideChuckerCollector(
        @ActivityContext context: Context
    ): ChuckerCollector = ChuckerCollector(context)

}