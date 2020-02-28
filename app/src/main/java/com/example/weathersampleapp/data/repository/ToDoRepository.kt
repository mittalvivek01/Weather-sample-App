package com.example.weathersampleapp.data.repository

import com.example.weathersampleapp.base.BaseRepository
import com.example.weathersampleapp.common.response.ApiResponse
import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.service.ToDoListService


class ToDoRepository(private val service: ToDoListService) : BaseRepository() {

    suspend fun getToDoList(): ApiResponse<List<WeatherResponseModel>> {
        return handleRequest { service.getToDoList() }
    }

}