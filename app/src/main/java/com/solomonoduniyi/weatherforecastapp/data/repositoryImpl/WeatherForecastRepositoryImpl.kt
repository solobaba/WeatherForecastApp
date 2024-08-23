package com.solomonoduniyi.weatherforecastapp.data.repositoryImpl

import com.solomonoduniyi.weatherforecastapp.data.mappers.WeatherForecastMapper
import com.solomonoduniyi.weatherforecastapp.data.mappers.WeatherForecastMapperDomain
import com.solomonoduniyi.weatherforecastapp.data.remote.WeatherApiService
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherLocationResponseDomain
import com.solomonoduniyi.weatherforecastapp.domain.repository.WeatherForecastRepository
import com.solomonoduniyi.weatherforecastapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
): WeatherForecastRepository {

    private val weatherForecastMapper: WeatherForecastMapper = WeatherForecastMapper()
    private val weatherForecastMapperDomain : WeatherForecastMapperDomain = WeatherForecastMapperDomain()

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Flow<ApiResult<WeatherLocationResponseDomain>> {
        return flow {
            emit(ApiResult.Loading(true))

            val getCurrentWeatherForecast = try {
                weatherApiService.getWeatherForecast(latitude, longitude)
            } catch (e: IOException) {
                emit(ApiResult.Error("Error loading weather"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading weather"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading weather"))
                return@flow
            }

            emit(ApiResult.Success(
                weatherForecastMapperDomain.mapDataToDomain(getCurrentWeatherForecast)
            ))
            emit(ApiResult.Loading(false))
        }
    }

    override suspend fun getWeatherByCity(
        cityName: String
    ): Flow<ApiResult<WeatherLocationResponseDomain>> {
        return flow {
            emit(ApiResult.Loading(true))

            val getWeatherByCity = try {
                weatherApiService.getWeatherByCity(cityName)
            } catch (e: IOException) {
                emit(ApiResult.Error("Error loading weather"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading weather"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading weather"))
                return@flow
            }

            emit(ApiResult.Success(
                weatherForecastMapperDomain.mapDataToDomain(getWeatherByCity)
            ))
            emit(ApiResult.Loading(false))
        }
    }
}