package com.android.weatherforecast.di.module

import android.app.Application
import com.android.weatherforecast.data.local.WeatherForecastDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherRoomModule {


        @Singleton
        @Provides
        fun provideDatabase(application: Application) = WeatherForecastDB.getInstance(application)

        @Singleton
        @Provides
        fun provideCurrentWeatherDao(database: WeatherForecastDB) = database.getCurrentWeatherDao()

        @Singleton
        @Provides
        fun provideFiveDaysWeatherDao(database: WeatherForecastDB) = database.getFiveDaysWeatherDao()

}
