package com.ilizma.api.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ProgramDTO(
    @SerialName(value = "hora") val hour: String? = null,
    @SerialName(value = "dia") val day: Int? = null,
    @SerialName(value = "nombre") val name: String? = null,
    @SerialName(value = "repeticion") val repeated: Int? = null,
)