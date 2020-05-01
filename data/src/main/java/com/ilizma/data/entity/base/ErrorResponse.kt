package com.ilizma.data.entity.base

import com.squareup.moshi.Json

class ErrorResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "message") val message: String,
    @Json(name = "errors") val errors: Map<String, List<String>>?,
    @Json(name = "reason") val reason: String?
)