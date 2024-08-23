package com.solomonoduniyi.weatherforecastapp.domain.model

data class WeatherLocationResponseDomain(
    val city: CityDomain?,
    val cnt: Int?,
    val cod: String?,
    val list: List<WeatherListDomain?> = listOf(),
    val message: Int?
)