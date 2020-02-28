package com.example.weathersampleapp.service

import com.example.weathersampleapp.data.models.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ToDoListService {

    @Headers(
        value = [
            "Content-Type: application/json",
            "accept: application/json"
        ]
    )
    @GET("/todos")
    suspend fun getToDoList(): Response<List<WeatherResponseModel>>

}