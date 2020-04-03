package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_item")
class WeatherItem (
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("id")
    var id:Int= 0

)