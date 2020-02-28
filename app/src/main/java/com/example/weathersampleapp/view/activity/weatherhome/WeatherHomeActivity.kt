package com.example.weathersampleapp.view.activity.weatherhome

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathersampleapp.BR
import com.example.weathersampleapp.R
import com.example.weathersampleapp.base.BaseActivity
import com.example.weathersampleapp.common.isNetWorkAvailable
import com.example.weathersampleapp.databinding.ActivityWeatherHomeBinding

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_weather_home.*

class WeatherHomeActivity : BaseActivity<WeatherHomeViewModel, ActivityWeatherHomeBinding>() {

    override val layoutRes = R.layout.activity_weather_home

    override val bindingVariable = BR.viewModel

    override val viewModelClass = WeatherHomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.title.value = "TO-Do List"


        // register data adapter
        with(rvToDos) {
            layoutManager = LinearLayoutManager(this@WeatherHomeActivity)
            adapter = WeatherAdapter(this@WeatherHomeActivity)
        }


        callApiWithNetworkCheck()

        // add data observer
        observeToDoListData()
        observeApiFailure()

        //Reload Api observer
        observeReloadEvent()
    }

    /*
      * Check Internet connection then call To-Do list api or show error.
      * */
    private fun callApiWithNetworkCheck() {
        if (isNetWorkAvailable()) {
            viewModel.isNetWorkAvailable.value = true

            viewModel.getTodoList()

        } else {
            viewModel.isNetWorkAvailable.value = false
        }
    }


    private fun observeToDoListData() {
        viewModel.toDoList.observe(this, Observer {
            if (it != null) {
                (rvToDos.adapter as WeatherAdapter).addItemList(it)
            }
        })
    }

    private fun observeApiFailure() {
        viewModel.apiFailureEvent.observe(this, Observer {
            viewModel.isNetWorkAvailable.value = false
            val snack = Snackbar.make(clTop, it.message, Snackbar.LENGTH_LONG)
            snack.show()
        })
    }

    private fun observeReloadEvent() {
        viewModel.apiReloadEvent.observe(this, Observer {
            callApiWithNetworkCheck()
        })
    }


}