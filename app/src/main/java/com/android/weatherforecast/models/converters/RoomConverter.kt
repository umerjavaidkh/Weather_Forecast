package com.android.weatherforecast.models.converters

import androidx.room.TypeConverter
import com.android.weatherforecast.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RoomObjectConverter{

    companion object {


       /* @TypeConverter
        fun fromStringToFiveDaysWeather(value: String?): FiveDaysWeather? {
            return Gson().fromJson(value, FiveDaysWeather::class.java)
        }


        @TypeConverter
        fun fromFiveDaysWeatherToString(value: FiveDaysWeather?): String? {
            return Gson().toJson(value)
        }
      */


        @JvmStatic
        @TypeConverter
        fun fromStringToCity(value: String?): City? {
            return Gson().fromJson(value, City::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromCityToString(value: City?): String? {
            return Gson().toJson(value)
        }


        @JvmStatic
        @TypeConverter
        fun fromStringToSYS(value: String?): Sys? {
            return Gson().fromJson(value, Sys::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromSYSToString(value: Sys?): String? {
            return Gson().toJson(value)
        }



        @JvmStatic
        @TypeConverter
        fun fromStringToClouds(value: String?): Clouds? {
            return Gson().fromJson(value, Clouds::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromCloudsToString(value: Clouds?): String? {
            return Gson().toJson(value)
        }


        @JvmStatic
        @TypeConverter
        fun fromStringToCoord(value: String?): Coord? {
            return Gson().fromJson(value, Coord::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromCoordToString(value: Coord?): String? {
            return Gson().toJson(value)
        }


        @JvmStatic
        @TypeConverter
        fun fromStringToItemHourly(value: String?): ItemHourly? {
            return Gson().fromJson(value, ItemHourly::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromItemHourlyToString(value: ItemHourly?): String? {
            return Gson().toJson(value)
        }



        @JvmStatic
        @TypeConverter
        fun fromStringToMain(value: String?): Main? {
            return Gson().fromJson(value, Main::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromItemMainToString(value: Main?): String? {
            return Gson().toJson(value)
        }



        @JvmStatic
        @TypeConverter
        fun fromStringToWeatherItem(value: String?): WeatherItem? {
            return Gson().fromJson(value, WeatherItem::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromItemWeatherItemToString(value: WeatherItem?): String? {
            return Gson().toJson(value)
        }


        @JvmStatic
        @TypeConverter
        fun fromStringToWind(value: String?): Wind? {
            return Gson().fromJson(value, Wind::class.java)
        }

        @JvmStatic
        @TypeConverter
        fun fromItemWindToString(value: Wind?): String? {
            return Gson().toJson(value)
        }


        //ItemHourly

        @JvmStatic
        @TypeConverter
        fun storedStringToMyItemHourly(data: String?): List<ItemHourly?>? {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType =
                object : TypeToken<List<ItemHourly?>?>() {}.type
            return gson.fromJson<List<ItemHourly?>>(data, listType)
        }

        @JvmStatic
        @TypeConverter
        fun myItemHourlyToStoredString(myObjects: List<ItemHourly?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


        @JvmStatic
        @TypeConverter
        fun myItemHourlyToStoredString(value: String?): MutableList<WeatherItem>? {
            if (value == null) {
                return null
            }
            val listType = object : TypeToken<MutableList<WeatherItem>>() {}.type
            return Gson().fromJson<MutableList<WeatherItem>>(value, listType)


        }


        @JvmStatic
        @TypeConverter
        fun myWeatherItemToStoredString(myObjects: List<WeatherItem?>?): String? {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


    }
}