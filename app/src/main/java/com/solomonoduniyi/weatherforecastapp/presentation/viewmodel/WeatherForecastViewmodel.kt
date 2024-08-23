package com.solomonoduniyi.weatherforecastapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.solomonoduniyi.weatherforecastapp.data.remote.response.WeatherLocationResponse
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherLocationResponseDomain
import com.solomonoduniyi.weatherforecastapp.domain.repository.WeatherForecastRepository
import com.solomonoduniyi.weatherforecastapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
): ViewModel() {
    private var _isLoadingFlow = MutableStateFlow(false)
    val isLoadingFlow: Flow<Boolean> = _isLoadingFlow

//    private val _observeAllTransaction = MutableStateFlow<GetAllTransactionsResp?>(null)
//    val observeAllTransaction = _observeAllTransaction.asLiveData()

    private val _weatherData = MutableStateFlow<WeatherLocationResponseDomain?>(null)
    val weatherData = _weatherData.asStateFlow()

    fun fetchWeather(
        latitude: Double,
        longtitude: Double
    ) {
        viewModelScope.launch {
            _isLoadingFlow.value = true

            weatherForecastRepository.getWeatherForecast(
                latitude, longtitude
            ).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _isLoadingFlow.value = false
                    }
                    is ApiResult.Loading -> {
                        _isLoadingFlow.value = true
                        result.isLoading
                    }
                    is ApiResult.Success -> {
                        _isLoadingFlow.value = false
                        result.data.let {
                           _weatherData.value = it
                        }
                    }
                }
            }
        }
    }
}