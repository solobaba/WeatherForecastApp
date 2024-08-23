package com.solomonoduniyi.weatherforecastapp.domain.model

import com.squareup.moshi.Json

data class RainDomain(
    @Json(name = "`3h`")
    val jsonMember3h: Double?
)