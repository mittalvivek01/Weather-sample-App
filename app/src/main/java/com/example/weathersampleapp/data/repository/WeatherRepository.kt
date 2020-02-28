package com.example.weathersampleapp.data.repository

import com.example.weathersampleapp.base.BaseRepository
import com.example.weathersampleapp.common.response.ApiResponse
import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.service.WeatherService


class WeatherRepository(private val service: WeatherService) : BaseRepository() {

    suspend fun getWeatherDetails(city:String,appId:String): ApiResponse<WeatherResponseModel> {
        return handleRequest { service.getWeatherDetail(city,appId) }
    }

}