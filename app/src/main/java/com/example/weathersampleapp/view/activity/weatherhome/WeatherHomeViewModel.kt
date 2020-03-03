package com.example.weathersampleapp.view.activity.weatherhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weathersampleapp.base.BaseViewModel
import com.example.weathersampleapp.common.response.ErrorResponse
import com.example.weathersampleapp.common.retrofit.RetrofitApiClient
import com.example.weathersampleapp.common.round
import com.example.weathersampleapp.constant.Constants
import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.data.models.X
import com.example.weathersampleapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherHomeViewModel : BaseViewModel() {

    val apiFailureEvent = MutableLiveData<ErrorResponse>()
    val isNetWorkAvailable = MutableLiveData<Boolean>()

    val apiRequestInProgress = MutableLiveData<Boolean>()
    val apiReloadEvent = MutableLiveData<Boolean>()

    var repository = WeatherRepository(RetrofitApiClient.callWeatherRetrofit())


    var location = MutableLiveData<String>()
    var temprature = MutableLiveData<String>()
    var minMaxTemp = MutableLiveData<String>()
    var weather = MutableLiveData<String>()
    var wind = MutableLiveData<String>()

    val weeklyWeatherList = MutableLiveData<List<X>>()


    fun getWeatherDetails() {

        viewModelScope.launch {
            apiRequestInProgress.value = true
            val response = repository.getWeatherDetails("Delhi,IN", Constants.APP_ID)
            if (response.isSuccessful) {
                response.data.let {

                    if (it != null) {
                        setData(it)
                    }

                }
            } else {
                apiFailureEvent.value = response.errorResponse
            }


            apiRequestInProgress.value = false
        }
    }


    fun getWeeklyWeatherDetails() {

        viewModelScope.launch {
            apiRequestInProgress.value = true
            val response = repository.getWeeklyWeatherDetails(Constants.BASE_URL_DAILY)
            if (response.isSuccessful) {
                response.data.let {

                    if (it != null) {
                        weeklyWeatherList.value = it.list
                    }

                }
            }


            apiRequestInProgress.value = false
        }
    }


    fun callApiReloadEvent() {
        apiReloadEvent.value = true
    }

    fun setData(it: WeatherResponseModel) {
        location.value = it.name
        temprature.value = (it.main.temp - 273.13).round(2).toString() + " C"
        minMaxTemp.value =
            (it.main.temp_min - 273.13).round(2).toString() + " C" + "/" + (it.main.temp_max - 273.13).round(
                2
            ).toString() + " C"
        weather.value = it.weather[0].description
        wind.value = (it.wind.speed).toString() + " m/s"

    }


}