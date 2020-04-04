package com.android.weatherforecast.ui.fivedayactivity.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.app_utils.AppUtil
import com.android.weatherforecast.app_utils.capitalizeWords
import com.android.weatherforecast.databinding.ItemCityLocationBinding
import com.android.weatherforecast.databinding.ItemFiveDayBinding
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.ui.selection_activity.adapter.CityListAdapter
import java.util.*


/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [CurrentCityViewHolder]]
 */
class CurrentCityViewHolder(private val binding: ItemCityLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        currentWeather: CurrentWeather,
        onItemClickListener: CityListAdapter.OnItemClickListener? = null
    ) {

        val calendar =
            Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = currentWeather.dt * 1000L
        try {
            if (currentWeather.main?.temp!! < 0 && currentWeather.main?.temp!! > -0.5) {
                currentWeather.main?.temp = 0.0
            }

            binding.cityTime.setText(AppUtil.getTime(calendar, binding.cityTime.context))

            binding.cityName.text = currentWeather.name+ ", "+currentWeather.sys?.country

            binding.tempWeather.text =
                currentWeather?.main?.temp.toString() + " \u2103" + " " + currentWeather.weather?.get(
                    0
                )?.main


            currentWeather.weather?.get(0)?.description?.let{

                binding.description.text=it.capitalizeWords()
            }


            currentWeather?.weather?.get(0)?.id.let {
                it?.let { it1 ->
                    AppUtil.setWeatherIcon(
                        binding.cityTime.context, binding.weatherImageView,
                        it1
                    )
                }
            }
        } catch (ex: Exception) {

        }
        onItemClickListener?.let { listener ->
            binding.root.setOnClickListener {
                listener.onItemClicked(currentWeather, binding.clickButton)
            }
        }
    }
}