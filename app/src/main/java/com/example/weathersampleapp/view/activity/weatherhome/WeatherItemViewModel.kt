package com.example.weathersampleapp.view.activity

import androidx.lifecycle.MutableLiveData

import com.example.weathersampleapp.base.BaseViewModel
import com.example.weathersampleapp.data.models.WeatherResponseModel

class WeatherItemViewModel(item: WeatherResponseModel) : BaseViewModel() {

    val toDoTitle = MutableLiveData<String>()
    val toDoStatus = MutableLiveData<String>()

    init {
        toDoTitle.value = item.title.capitalize()
        toDoStatus.value = item.completed.toString().capitalize()
    }

}