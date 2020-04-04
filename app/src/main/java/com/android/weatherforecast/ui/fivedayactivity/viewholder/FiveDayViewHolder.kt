package com.android.weatherforecast.ui.fivedayactivity.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.app_utils.AppUtil
import com.android.weatherforecast.app_utils.Constants
import com.android.weatherforecast.app_utils.capitalizeWords
import com.android.weatherforecast.databinding.ItemFiveDayBinding
import com.android.weatherforecast.models.FiveDaysWeather
import com.android.weatherforecast.models.ItemHourly
import com.android.weatherforecast.ui.fivedayactivity.adapter.FiveDayListAdapter
import java.util.*


/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [FiveDayViewHolder]]
 */
class FiveDayViewHolder(private val binding: ItemFiveDayBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fiveDaysWeather : FiveDaysWeather,position:Int, onItemClickListener: FiveDayListAdapter.OnItemClickListener? = null) {

        val calendar =
            Calendar.getInstance(TimeZone.getDefault())

         var hourOfDay= calendar.get(Calendar.HOUR_OF_DAY)

        //index mapping
         var index=0

        if(hourOfDay==0){
            index= hourOfDay
        }
        else if(fiveDaysWeather.list?.size!! < 8){
            index= 7-(hourOfDay/3)   //0 1 2 3 4 5 6 7  // hour = 4  item = 4
        }else{
            index= (hourOfDay/3)
        }

        var itemHourly = fiveDaysWeather.list?.get(index)

        itemHourly?.let {

            calendar.timeInMillis = itemHourly.dt * 1000L

            binding.dayNameTextView.setText(Constants.DAYS_OF_WEEK.get(position))

            if (itemHourly.main!!.temp< 0 && itemHourly.main!!.temp > -0.5) {
                itemHourly.main!!.temp = 0.0
            }

            if (itemHourly.main?.temp_max!! < 0 && itemHourly.main?.temp_max!! > -0.5) {
                itemHourly.main?.temp_max = itemHourly.main!!.temp
            }else if(itemHourly.main?.temp_max==0.0){
                itemHourly.main?.temp_max = itemHourly.main!!.temp
            }
            if (itemHourly.main?.temp_min!! < 0 && itemHourly.main?.temp_min!! > -0.5) {
                itemHourly.main?.temp_min = itemHourly.main!!.temp
            }else if(itemHourly.main?.temp_min==0.0){
                itemHourly.main?.temp_min = itemHourly.main!!.temp
            }

            binding.tempTextView.setText(
                String.format(
                    Locale.getDefault(),
                    "%.0f°",
                    itemHourly.main!!.temp
                )
            )



            itemHourly.weather?.get(0)?.description?.let {

                binding.desTextView.text=it.capitalizeWords()
            }

            binding.minTempTextView.setText(
                String.format(
                    Locale.getDefault(),
                    "%.0f°",
                    itemHourly.main?.temp_min
                )
            )
            binding.maxTempTextView.setText(
                String.format(
                    Locale.getDefault(),
                    "%.0f°",
                    itemHourly.main?.temp_max
                )
            )
            itemHourly.weather?.get(0)?.id?.let {
                AppUtil.setWeatherIcon(binding.maxTempTextView.context, binding.weatherImageView,
                    it
                )
            }

            onItemClickListener?.let { listener ->
                binding.root.setOnClickListener {
                    listener.onItemClicked(fiveDaysWeather, binding.clickButton)
                }
            }
        }

    }

    fun fahrenToCelsius(temp:Double):Double{

        var a = temp
        val b: Double = a - 32
        val c = b * 5 / 9
        return c
    }
}