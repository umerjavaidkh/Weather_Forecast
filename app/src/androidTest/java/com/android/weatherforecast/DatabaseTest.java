package com.android.weatherforecast;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.android.weatherforecast.data.local.WeatherForecastDB;
import com.android.weatherforecast.data.local.dao.CurrentWeatherDao;
import com.android.weatherforecast.data.local.dao.FiveDaysWeatherDao;


import org.junit.After;
import org.junit.Before;

public abstract class DatabaseTest {

    // system under test
     WeatherForecastDB weatherForecastDB;


    public CurrentWeatherDao getCurrentWeatherDao(){
        return weatherForecastDB.getCurrentWeatherDao();
    }

    public FiveDaysWeatherDao getFiveDaysWeatherDao(){
        return weatherForecastDB.getFiveDaysWeatherDao();
    }

    @Before
    public void init(){
        try {
            weatherForecastDB = Room.inMemoryDatabaseBuilder(
                    ApplicationProvider.getApplicationContext(),
                    WeatherForecastDB.class
            ).build();
        }catch (Exception ex){

            ex.toString();
        }
    }

    @After
    public void finish(){
        weatherForecastDB.close();
    }
}






