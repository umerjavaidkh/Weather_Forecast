package com.android.weatherforecast.app_utils

 object Constants{

     const val Lang: String = "en"
     const val API_KEY = "42a13601b9d863e3db6c8c8ec39d8b1a"
     const val TIME_TO_PASS = 6 * 600000.toLong()
     const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
     const val UNITS = "metric"
     const val CITY_NAME="city_name"
     const val CITY_LAT="city_lat"
     const val CITY_LON="city_lon"
     const val HUMIDITY="humidity"
     const val TEMPERATURE="temp"
     const val WIND="wind"
     const val WEATHER_ID="weather_id"
     const val MIN_ALLOWED_CITIES:Int=3
     const  val MAX_ALLOWED_CITIES:Int=7

     @JvmStatic
    val DAYS_OF_WEEK = arrayOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )

     @JvmStatic
    val MONTH_NAME = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
     @JvmStatic
     val WEATHER_STATUS = arrayOf(
         "Thunderstorm",
         "Drizzle",
         "Rain",
         "Snow",
         "Atmosphere",
         "Clear",
         "Few Clouds",
         "Broken Clouds",
         "Cloud"
     )
}