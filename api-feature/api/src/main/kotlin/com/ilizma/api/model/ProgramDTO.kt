package com.ilizma.api.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ProgramDTO(
    @Json(name = "hora") val hour: String? = null,
    @Json(name = "dia") val day: Int? = null,
    @Json(name = "nombre") val name: String? = null,
    @Json(name = "repeticion") val repeated: Int? = null,
)