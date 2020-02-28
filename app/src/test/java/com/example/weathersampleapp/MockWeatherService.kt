package com.example.weathersampleapp


import com.example.weathersampleapp.data.models.*
import com.example.weathersampleapp.service.WeatherService
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

class MockWeatherService : WeatherService {
    var requireSuccessfulResponse: Boolean = true

    override suspend fun getWeatherDetail(
        city: String,
        appId: String
    ): Response<WeatherResponseModel> {
        if (requireSuccessfulResponse) {
            return getMockedWeatherResponse()
        } else {
            return getErrorResponse()
        }
    }


    private fun getMockedWeatherResponse(): Response<WeatherResponseModel> {
        return Response.success(
            WeatherResponseModel(
                "", Clouds(0), 200,
                Coord(11.22, 33.22), 1, 1, Main(22.22, 23, 23, 23.2, 22.2, 12.2),
                "Delhi", Sys("IN", 2, 4, 4, 4), 3, 32,
                listOf(Weather("Haze", "fd", 2, "dd")), Wind(2, 3.33, 3.33)
            )
        )
    }

    private fun <T> getErrorResponse(): Response<T> {
        return Response.error(
            getErrorResponseBody(), okhttp3.Response.Builder() //
                .body(getErrorResponseBody())
                .code(500)
                .message("Response.error()")
                .protocol(Protocol.HTTP_1_1)
                .request(Request.Builder().url("http://localhost/").build())
                .build()
        )
    }

    private fun getErrorResponseBody(): ResponseBody {
        val jsonObject = JsonObject()
        jsonObject.addProperty("errorCode", 400)
        jsonObject.addProperty("message", "Mocked error message")

        return ResponseBody.create(MediaType.get("application/json"), jsonObject.toString())
    }


}