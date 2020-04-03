package com.android.weatherforecast.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "item_hourly")
data class ItemHourly (

    @PrimaryKey(autoGenerate = true)
    var id:Int= 0,

    @SerializedName("dt")
    var dt:Int= 0,
    @SerializedName("dt_txt")
    var dt_txt: String? = "",
    @SerializedName("weather")
    var weather: List<WeatherItem>? = null,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("wind")
    var wind: Wind? = null

)
