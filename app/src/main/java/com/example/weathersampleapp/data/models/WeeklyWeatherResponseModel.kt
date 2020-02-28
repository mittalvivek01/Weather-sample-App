package com.example.weathersampleapp.data.models

data class WeeklyWeatherResponseModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Int
)

data class City(
    val country: String,
    val geoname_id: Int,
    val iso2: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val population: Int,
    val type: String
)

data class X(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val humidity: Int,
    val pressure: Double,
    val snow: Double,
    val speed: Double,
    val temp: Temp,
    val weather: List<WeatherWeek>
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class WeatherWeek(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)