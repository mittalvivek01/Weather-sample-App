package com.example.weathersampleapp.view.activity.weatherhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weathersampleapp.base.BaseViewModel
import com.example.weathersampleapp.common.Retrofit.RetrofitApiClient
import com.example.weathersampleapp.common.response.ErrorResponse
import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.data.repository.ToDoRepository
import kotlinx.coroutines.launch

class WeatherHomeViewModel : BaseViewModel() {

    val toDoList = MutableLiveData<List<WeatherResponseModel>>()
    val apiFailureEvent = MutableLiveData<ErrorResponse>()
    val isNetWorkAvailable = MutableLiveData<Boolean>()

    val apiRequestInProgress = MutableLiveData<Boolean>()
    val apiReloadEvent = MutableLiveData<Boolean>()

    var repository= ToDoRepository(RetrofitApiClient.callTodoRetrofit())

    fun getTodoList() {

        viewModelScope.launch {
            apiRequestInProgress.value = true
            val response = repository.getToDoList()
            if (response.isSuccessful) {
                response.data.let {
                    toDoList.value = it
                }
            } else {
                apiFailureEvent.value = response.errorResponse
            }


            apiRequestInProgress.value = false
        }
    }

    fun callApiReloadEvent() {
        apiReloadEvent.value = true
    }

}