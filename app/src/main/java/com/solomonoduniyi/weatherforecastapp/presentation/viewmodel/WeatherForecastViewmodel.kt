package com.solomonoduniyi.weatherforecastapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solomonoduniyi.weatherforecastapp.domain.repository.WeatherForecastRepository
import com.solomonoduniyi.weatherforecastapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewmodel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
): ViewModel() {
    private var _isLoadingFlow = MutableStateFlow(false)
    val isLoadingFlow: Flow<Boolean> = _isLoadingFlow

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
                        result.data
                    }
                }
            }
        }
    }
}