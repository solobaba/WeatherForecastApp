package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherLocationResponseDomain(
    val city: CityDomain?,
    val cnt: Int?,
    val cod: String?,
    val list: List<WeatherListDomain>? = listOf(),
    val message: Int?
): Parcelable