package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RainDomain(
    @Json(name = "`3h`")
    val jsonMember3h: Double?
): Parcelable