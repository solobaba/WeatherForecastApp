package com.solomonoduniyi.weatherforecastapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SysDomain(
    val pod: String?
): Parcelable