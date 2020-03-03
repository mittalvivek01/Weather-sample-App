package com.example.weathersampleapp.service

import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.data.models.WeeklyWeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

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

    @GET
    suspend fun getWeeklyWeatherDetail(
        @Url url : String): Response<WeeklyWeatherResponseModel>

}