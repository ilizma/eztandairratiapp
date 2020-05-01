package com.ilizma.eztandairratiapp.di.module.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.ilizma.data.net.BaseHttpClient
import com.ilizma.data.net.BaseRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.*
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun okHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient = baseHttpClient.okHttpClient

    @Provides
    @Singleton
    fun retrofit(baseRetrofit: BaseRetrofit): Retrofit = baseRetrofit.retrofit

    @Provides
    @Singleton
    fun chuckerCollector(context: Context): ChuckerCollector = ChuckerCollector(context = context)

}