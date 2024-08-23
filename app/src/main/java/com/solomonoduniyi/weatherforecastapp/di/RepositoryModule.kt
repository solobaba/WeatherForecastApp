package com.solomonoduniyi.weatherforecastapp.di

import com.solomonoduniyi.weatherforecastapp.data.repositoryImpl.WeatherForecastRepositoryImpl
import com.solomonoduniyi.weatherforecastapp.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherForecastRepository(
        weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository
}