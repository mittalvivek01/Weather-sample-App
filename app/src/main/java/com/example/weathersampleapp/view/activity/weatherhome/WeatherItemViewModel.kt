package com.example.weathersampleapp.view.activity

import androidx.lifecycle.MutableLiveData
import com.example.weathersampleapp.base.BaseViewModel
import com.example.weathersampleapp.common.round
import com.example.weathersampleapp.data.models.X

class WeatherItemViewModel(item: X) : BaseViewModel() {

    val date = MutableLiveData<String>()
    val maxTemp = MutableLiveData<String>()
    val minTemp = MutableLiveData<String>()


    init {
        // toDoTitle.value = item.title.capitalize()


        maxTemp.value = (item.temp.max - 273.13).round(2).toString() + " C"
        minTemp.value = (item.temp.min - 273.13).round(2).toString() + " C"


    }

}