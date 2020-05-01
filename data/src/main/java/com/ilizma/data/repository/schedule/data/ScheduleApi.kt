package com.ilizma.data.repository.schedule.data

import com.ilizma.data.entity.schedule.DataResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

interface ScheduleApi {

    @GET("controller/program")
    fun getSchedule(): Single<Result<DataResponse>>

}