package com.ilizma.eztandairratiapp.di.module.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun schedule(
        retrofit: Retrofit,
    ): ScheduleApi = retrofit.create(ScheduleApi::class.java)

}