package com.ilizma.net

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseHttpClientModule {

    @Provides
    @Singleton
    fun retrofit(
        chuckerCollector: ChuckerCollector,
        context: Context,
    ): BaseHttpClient = BaseHttpClient(
        chuckerCollector,
        context,
    )

}