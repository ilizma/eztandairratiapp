package com.ilizma.net

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun retrofit(
        baseRetrofit: BaseRetrofit,
    ): Retrofit = baseRetrofit.retrofit

}