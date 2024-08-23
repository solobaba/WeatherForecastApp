package com.solomonoduniyi.weatherforecastapp.data.remote.response

data class WeatherLocationResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<WeatherList?> = listOf(),
    val message: Int?
)