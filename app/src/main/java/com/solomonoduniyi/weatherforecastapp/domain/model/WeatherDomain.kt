package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDomain(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
): Parcelable