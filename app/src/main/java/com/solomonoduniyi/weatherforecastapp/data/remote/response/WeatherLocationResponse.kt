package com.solomonoduniyi.weatherforecastapp.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherLocationResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    @Json(name = "list")
    val list: List<WeatherList>? = listOf(),
    val message: Int?
)