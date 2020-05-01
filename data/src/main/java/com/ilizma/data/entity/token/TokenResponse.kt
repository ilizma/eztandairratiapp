package com.ilizma.data.entity.token

import com.ilizma.domain.entity.base.ResponseObject
import com.squareup.moshi.Json

class TokenResponse(
    @Json(name = "access_token") val accessToken: String
) : ResponseObject<String> {

    override fun toDomain(): String = accessToken

}