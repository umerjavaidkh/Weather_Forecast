package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "main")
class Main (
    @SerializedName("temp")
    var temp : Double= 0.0,
    @SerializedName("temp_min")
    var temp_min : Double= 0.0,
    @SerializedName("grnd_level")
    var grndLevel : Double= 0.0,
    @SerializedName("humidity")
    var humidity :Int= 0,
    @SerializedName("pressure")
    var pressure : Double= 0.0,
    @SerializedName("sea_level")
    var seaLevel : Double= 0.0,
    @SerializedName("temp_max")
    var temp_max : Double= 0.0,

    @SerializedName("weatherCode")
    val weatherCode: Int =0

) {

}