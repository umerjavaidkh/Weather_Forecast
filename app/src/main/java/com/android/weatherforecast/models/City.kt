package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "city")
class City (
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("id")
    var id:Int = 0,
    @SerializedName("population")
    var population:Int= 0

)