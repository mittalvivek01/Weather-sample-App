package com.example.weathersampleapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.text.format.DateFormat
import java.util.*

fun Context.isNetWorkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()


fun getDate(time: Long): String? {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = time * 1000
    return DateFormat.format("dd/MM", cal).toString()
}