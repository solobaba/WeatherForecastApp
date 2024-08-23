package com.solomonoduniyi.weatherforecastapp.domain.model

import com.squareup.moshi.Json

data class WeatherListDomain(
    val clouds: CloudsDomain?,
    val dt: Long?,
    @Json(name = "dt_txt")
    val dtTxt: String?,
    val main: MainDomain?,
    val pop: Double?,
    val rain: RainDomain?,
    val sys: SysDomain?,
    val visibility: Int?,
    val weather: List<WeatherDomain>?,
    val wind: WindDomain?
)