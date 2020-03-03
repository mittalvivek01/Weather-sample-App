package com.example.weathersampleapp.view.activity

import androidx.lifecycle.MutableLiveData
import com.example.weathersampleapp.base.BaseViewModel
import com.example.weathersampleapp.common.getDate
import com.example.weathersampleapp.common.round
import com.example.weathersampleapp.data.models.X

class WeatherItemViewModel(item: X) : BaseViewModel() {

    val date = MutableLiveData<String>()
    val maxTemp = MutableLiveData<String>()
    val minTemp = MutableLiveData<String>()


    init {
        date.value = getDate(item.dt.toLong())

        maxTemp.value = (item.temp.max - 273.13).round(2).toString() + " \u2103"
        minTemp.value = (item.temp.min - 273.13).round(2).toString() + " \u2103"


    }

}