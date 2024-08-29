package com.solomonoduniyi.weatherforecastapp.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Int?
): Parcelable