package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "wind")
class Wind (
    @SerializedName("deg")
    var deg : Double= 0.0,
    @SerializedName("speed")
    var speed : Double= 0.0

)