package com.solomonoduniyi.weatherforecastapp.data.mappers

import com.solomonoduniyi.weatherforecastapp.data.local.entity.CityEntity
import com.solomonoduniyi.weatherforecastapp.data.local.entity.CoordEntity
import com.solomonoduniyi.weatherforecastapp.data.local.entity.WeatherLocationEntity
import com.solomonoduniyi.weatherforecastapp.data.remote.response.City
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Clouds
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Coord
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Main
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Rain
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Sys
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Weather
import com.solomonoduniyi.weatherforecastapp.data.remote.response.WeatherList
import com.solomonoduniyi.weatherforecastapp.data.remote.response.WeatherLocationResponse
import com.solomonoduniyi.weatherforecastapp.data.remote.response.Wind
import com.solomonoduniyi.weatherforecastapp.domain.model.CityDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.CloudsDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.CoordDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.MainDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.RainDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.SysDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherListDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherLocationResponseDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.WindDomain

class WeatherForecastMapperData {
    fun mapDataToEntity(weatherLocationResponse: WeatherLocationResponse?) : WeatherLocationEntity? {
        if (weatherLocationResponse == null) return null
        return with(weatherLocationResponse) {
            WeatherLocationEntity(
                city = city?.toCityEntity(),
                cnt = cnt,
                cod = cod,
                list = list?.map { it.toWeatherList() },
                message = message
            )
        }
    }

    fun City.toCityEntity() : CityEntity {
        return CityEntity(
            cityCoord = coord?.toCoord(),
            cityCountry = country,
            cityId = id,
            cityName = name,
            cityPopulation = population,
            citySunrise = sunrise,
            citySunset = sunset,
            cityTimezone = timezone
        )
    }

    fun WeatherList.toWeatherList() : WeatherList {
        return WeatherList(
            clouds = clouds?.toClouds(),
            dt = dt,
            dtTxt = dtTxt,
            main = main?.toMain(),
            pop = pop,
            rain = rain?.toRain(),
            sys = sys?.toSys(),
            visibility = visibility,
            weather = weather?.map { it.toWeather() },
            wind = wind?.toWind()
        )
    }

    fun Coord.toCoord() : CoordEntity {
        return CoordEntity(
            lat = lat,
            lon = lon
        )
    }

    fun Clouds.toClouds() : Clouds {
        return Clouds(
            all = all
        )
    }

    fun Main.toMain() : Main {
        return Main(
            feelsLike = feelsLike,
            grndLevel = grndLevel,
            humidity = humidity,
            pressure = pressure,
            seaLevel = seaLevel,
            temp = temp,
            tempKf = tempKf,
            tempMax = tempMax,
            tempMin = tempMin
        )
    }

    fun Rain.toRain() : Rain {
        return Rain(
            jsonMember3h = jsonMember3h
        )
    }

    fun Sys.toSys() : Sys {
        return Sys(
            pod = pod
        )
    }

    fun Weather.toWeather() : Weather {
        return Weather(
            description = description,
            icon = icon,
            id = id,
            main = main
        )
    }

    fun Wind.toWind() : Wind {
        return Wind(
            deg = deg,
            gust = gust,
            speed = speed
        )
    }
}