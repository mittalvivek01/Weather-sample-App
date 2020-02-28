package com.example.weathersampleapp.common.Retrofit

import com.example.weathersampleapp.constant.Constants
import com.example.weathersampleapp.service.ToDoListService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitApiClient {

    private val interceptor = HttpLoggingInterceptor()
    private val logging: Interceptor = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun callTodoRetrofit(): ToDoListService = retrofit.create(ToDoListService::class.java)


}