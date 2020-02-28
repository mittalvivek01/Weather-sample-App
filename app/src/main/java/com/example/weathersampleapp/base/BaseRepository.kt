package com.example.weathersampleapp.base


import com.example.weathersampleapp.common.response.ApiResponse
import com.example.weathersampleapp.common.response.ErrorResponse
import retrofit2.Response
import java.net.SocketTimeoutException

private const val TAG = "BaseRepository"

abstract class BaseRepository {

    open suspend fun <T> handleRequest(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.isSuccessful) {
                ApiResponse.Success(apiResponse.body())
            } else {
                // handleFailureResponse(apiResponse.errorBody())
                ApiResponse.Failure(ErrorResponse(400, "Something Went Wrong."))

            }
        } catch (ex: SocketTimeoutException) {
            return ApiResponse.Failure(ErrorResponse(401, "Socket timeout Exception"))

        } catch (ex: Exception) {
            //Logger.Error(TAG, ex.localizedMessage)
            // ex.printStackTrace()

            return ApiResponse.Failure(ErrorResponse(402, "Something Went Wrong."))
        }
    }


}