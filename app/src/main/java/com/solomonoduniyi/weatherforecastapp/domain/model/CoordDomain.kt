package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordDomain(
    val lat: Double?,
    val lon: Double?
): Parcelable