package com.ilizma.net

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ChuckerCollectorModule {

    @Provides
    @Singleton
    fun provideChuckerCollector(
        context: Context
    ): ChuckerCollector = ChuckerCollector(context)

}