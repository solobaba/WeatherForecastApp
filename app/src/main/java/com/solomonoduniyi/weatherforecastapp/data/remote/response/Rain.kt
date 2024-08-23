package com.solomonoduniyi.weatherforecastapp.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    @Json(name = "`3h`")
    val jsonMember3h: Double?
): Parcelable