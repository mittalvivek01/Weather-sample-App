package com.example.weathersampleapp.view.activity.weatherhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weathersampleapp.R
import com.example.weathersampleapp.data.models.WeatherResponseModel
import com.example.weathersampleapp.data.models.X
import com.example.weathersampleapp.databinding.ItemWeatherBinding
import com.example.weathersampleapp.view.activity.WeatherItemViewModel


class WeatherAdapter(private val parent: WeatherHomeActivity) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weeklyList = arrayListOf<X>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemWeatherBinding>(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_weather, viewGroup, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weeklyList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.setData(weeklyList[index])
    }


    fun addItemList(list: List<X>) {
        weeklyList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: X) {
            binding.viewModel = WeatherItemViewModel(item)
            binding.executePendingBindings()

        }


    }

}