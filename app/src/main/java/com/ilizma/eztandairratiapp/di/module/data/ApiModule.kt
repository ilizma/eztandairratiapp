package com.ilizma.eztandairratiapp.di.module.data

import com.ilizma.data.repository.schedule.data.ScheduleApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun schedule(retrofit: Retrofit): ScheduleApi =
        retrofit.create(ScheduleApi::class.java)

}