package com.example.weathersampleapp.constant

object Constants {

    // http://api.openweathermap.org/data/2.5/weather?q=Delhi,IN&APPID=6cb6a8d6d3e667f888485993fc6f2c11
//api.openweathermap.org/data/2.5/forecast?q={city name}&appid={your api key}
    const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    const val APP_ID = "6cb6a8d6d3e667f888485993fc6f2c11"

    const val BASE_URL_DAILY =
        "http://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=6cb6a8d6d3e667f888485993fc6f2c11"
}