package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "coord")
class Coord (
    @SerializedName("lon")
    var lon :Double = 0.0,
    @SerializedName("lat")
    var lat :Double = 0.0

)