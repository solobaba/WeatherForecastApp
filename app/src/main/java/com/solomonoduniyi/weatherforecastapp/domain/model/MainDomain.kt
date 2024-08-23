package com.solomonoduniyi.weatherforecastapp.domain.model

import com.squareup.moshi.Json

data class MainDomain(
    @Json(name = "feels_like")
    val feelsLike: Double?,
    @Json(name = "grnd_level")
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    @Json(name = "sea_level")
    val seaLevel: Int?,
    val temp: Double?,
    @Json(name = "temp_kf")
    val tempKf: Double?,
    @Json(name = "temp_max")
    val tempMax: Double?,
    @Json(name = "temp_min")
    val tempMin: Double?
)