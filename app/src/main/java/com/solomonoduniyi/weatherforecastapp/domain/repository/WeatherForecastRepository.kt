package com.solomonoduniyi.weatherforecastapp.domain.repository

import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherLocationResponseDomain
import com.solomonoduniyi.weatherforecastapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<ApiResult<WeatherLocationResponseDomain>>

    suspend fun getWeatherByCity(
        cityName: String
    ): Flow<ApiResult<WeatherLocationResponseDomain>>
}