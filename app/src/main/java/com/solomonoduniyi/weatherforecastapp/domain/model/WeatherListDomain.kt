package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable