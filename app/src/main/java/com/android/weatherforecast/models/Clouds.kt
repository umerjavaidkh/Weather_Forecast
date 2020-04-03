package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "clouds")
class Clouds (
    @SerializedName("all")
    var all : Int= 0

)