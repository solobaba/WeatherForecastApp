package com.solomonoduniyi.weatherforecastapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds(
    val all: Int?
): Parcelable