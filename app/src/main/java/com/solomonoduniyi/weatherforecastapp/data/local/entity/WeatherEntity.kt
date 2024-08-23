package com.solomonoduniyi.weatherforecastapp.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Weather
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Weather")
data class WeatherEntity(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
): Parcelable {
    @Ignore
    constructor(weather: Weather?) : this(
        description = weather?.description,
        icon = weather?.icon,
        id = weather?.id,
        main = weather?.main
    )
}