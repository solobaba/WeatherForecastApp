package com.solomonoduniyi.weatherforecastapp.data.remote

import com.solomonoduniyi.weatherforecastapp.data.remote.response.WeatherLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appID: String = Constants.APP_ID
    ): WeatherLocationResponse

    @GET("data/2.5/forecast")
    suspend fun getWeatherByCity(
        @Query("q") cityNamePattern: String,
        @Query("appid") appID: String = Constants.APP_ID
    ): WeatherLocationResponse
}