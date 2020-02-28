package com.example.weathersampleapp.service

import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.data.models.WeeklyWeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {

    @Headers(
        value = [
            "Content-Type: application/json",
            "accept: application/json"
        ]
    )

    @GET("weather")
    suspend fun getWeatherDetail(
        @Query("q") city: String,
        @Query("APPID") appId: String
    ): Response<WeatherResponseModel>

    @GET("forecast/daily")
    suspend fun getWeeklyWeatherDetail(  @Query("id") city: String,
                                         @Query("appid") appId: String): Response<WeeklyWeatherResponseModel>

}