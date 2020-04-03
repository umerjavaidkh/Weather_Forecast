package com.android.weatherforecast.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.weatherforecast.data.local.dao.CurrentWeatherDao
import com.android.weatherforecast.data.local.dao.FiveDaysWeatherDao
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.models.FiveDaysWeather
import com.android.weatherforecast.models.converters.RoomObjectConverter


@Database(
    entities = [FiveDaysWeather::class,CurrentWeather::class],
    version = DatabaseMigrations.DB_VERSION
)

@TypeConverters(value = arrayOf(RoomObjectConverter::class))

abstract class WeatherForecastDB : RoomDatabase() {

    /**
     * @return [CurrentWeatherDao]
     */
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao


    /**
     * @return [FiveDaysWeatherDao]
     */
    abstract fun getFiveDaysWeatherDao(): FiveDaysWeatherDao

    companion object {
        const val DB_NAME = "weather_forecast_database"

        @Volatile
        private var INSTANCE: WeatherForecastDB? = null

        fun getInstance(context: Context): WeatherForecastDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }


                synchronized(this) {


                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherForecastDB::class.java,
                        DB_NAME
                    )
                        .addMigrations(*DatabaseMigrations.MIGRATIONS)
                        .build()

                    INSTANCE = instance


                    return instance
                }




        }

    }
}