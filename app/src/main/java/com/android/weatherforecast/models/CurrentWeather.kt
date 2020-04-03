package com.android.weatherforecast.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.weatherforecast.models.CurrentWeather.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
 class CurrentWeather (

    @PrimaryKey
    @SerializedName("id")
    var id :Int= 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("dt")
    var dt :Int= 0,
    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("weather")
    var weather: List<WeatherItem>? = null,
    @SerializedName("cod")
    var cod :Int= 0,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("wind")
    var wind: Wind? = null


){

    companion object {
        const val TABLE_NAME = "CurrentWeather"
    }
}