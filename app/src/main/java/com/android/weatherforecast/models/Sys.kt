package com.android.weatherforecast.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "sys")
class Sys (
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("country")
    var country: String? = ""

)