package com.ilizma.api.data

import com.ilizma.api.model.ScheduleDTO
import de.jensklingenberg.ktorfit.http.GET

interface EztandaApi {

    @GET("controller/program")
    suspend fun getSchedule(): ScheduleDTO

}