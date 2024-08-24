package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WindDomain(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
): Parcelable