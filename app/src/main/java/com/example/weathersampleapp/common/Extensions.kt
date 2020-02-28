package com.example.weathersampleapp.common

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

fun Context.isNetWorkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()