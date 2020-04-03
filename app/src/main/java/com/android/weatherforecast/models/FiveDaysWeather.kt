package com.android.weatherforecast.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.weatherforecast.models.FiveDaysWeather.Companion.TABLE_NAME

import com.google.gson.annotations.SerializedName


@Entity(tableName = TABLE_NAME)
class FiveDaysWeather (



    @PrimaryKey
    @SerializedName("id")
    var id :Int= 0,

    @SerializedName("dt")
     var dt: Int = 0,

    @SerializedName("dt_txt")
    var dtTxt: String = "",

    @SerializedName("city")
    var city: City? = null,
    @SerializedName("cnt")
    var cnt:Int= 0,
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("message")
    var message :Double= 0.0,

    @SerializedName("list")
    var list: List<ItemHourly>? = null

){
    companion object {
        const val TABLE_NAME = "FiveDay"
    }
}