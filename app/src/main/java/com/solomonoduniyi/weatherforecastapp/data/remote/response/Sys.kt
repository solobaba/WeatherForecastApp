package com.solomonoduniyi.weatherforecastapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    val pod: String?
): Parcelable