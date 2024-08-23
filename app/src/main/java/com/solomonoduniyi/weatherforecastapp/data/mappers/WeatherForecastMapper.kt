package com.solomonoduniyi.weatherforecastapp.data.mappers

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

class WeatherForecastMapper {
    fun mapToDomain(weatherLocationResponseDomain: WeatherLocationResponseDomain?) : WeatherLocationResponse? {
        if (weatherLocationResponseDomain == null) return null
        return with(weatherLocationResponseDomain) {
            WeatherLocationResponse(
                city = city?.toCityDomain(),
                cnt = cnt,
                cod = cod,
                list = list.map { it?.toWeatherListDomain() },
                message = message
            )
        }
    }

    fun CityDomain.toCityDomain() : City {
        return City(
            coord = coord?.toCoordDomain(),
            country = country,
            id = id,
            name = name,
            population = population,
            sunrise = sunrise,
            sunset = sunset,
            timezone = timezone
        )
    }

    fun WeatherListDomain.toWeatherListDomain() : WeatherList {
        return WeatherList(
            clouds = clouds?.toCloudsDomain(),
            dt = dt,
            dtTxt = dtTxt,
            main = main?.toMainDomain(),
            pop = pop,
            rain = rain?.toRainDomain(),
            sys = sys?.toSysDomain(),
            visibility = visibility,
            weather = weather.map { it?.toWeatherDomain() },
            wind = wind?.toWindDomain()
        )
    }

    fun CoordDomain.toCoordDomain() : Coord {
        return Coord(
            lat = lat,
            lon = lon
        )
    }

    fun CloudsDomain.toCloudsDomain() : Clouds {
        return Clouds(
            all = all
        )
    }

    fun MainDomain.toMainDomain() : Main {
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

    fun RainDomain.toRainDomain() : Rain {
        return Rain(
            jsonMember3h = jsonMember3h
        )
    }

    fun SysDomain.toSysDomain() : Sys {
        return Sys(
            pod = pod
        )
    }

    fun WeatherDomain.toWeatherDomain() : Weather {
        return Weather(
            description = description,
            icon = icon,
            id = id,
            main = main
        )
    }

    fun WindDomain.toWindDomain() : Wind {
        return Wind(
            deg = deg,
            gust = gust,
            speed = speed
        )
    }
}