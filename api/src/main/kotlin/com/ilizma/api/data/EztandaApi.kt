package com.ilizma.api.data

import com.ilizma.api.model.ScheduleDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.Result
import retrofit2.http.GET

interface EztandaApi {

    @GET("controller/program")
    fun getSchedule(): Single<Result<ScheduleDTO>>

}