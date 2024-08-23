package com.solomonoduniyi.weatherforecastapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.solomonoduniyi.weatherforecastapp.data.local.dao.CurrentWeatherDao
import com.solomonoduniyi.weatherforecastapp.data.local.dao.ForecastDao
import com.solomonoduniyi.weatherforecastapp.data.local.entity.WeatherListEntity
import com.solomonoduniyi.weatherforecastapp.data.local.entity.WeatherLocationEntity
import com.solomonoduniyi.weatherforecastapp.utils.DataConverter

@Database(
    entities = [
        WeatherLocationEntity::class,
        WeatherListEntity::class
    ],
    version = 1
)

@TypeConverters(DataConverter::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract val currentWeatherDao: CurrentWeatherDao
    abstract val forecastDao: ForecastDao
}