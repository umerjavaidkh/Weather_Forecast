package com.android.weatherforecast

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.android.weatherforecast.data.local.WeatherForecastDB
import com.android.weatherforecast.models.CurrentWeather
import com.bumptech.glide.load.engine.Resource
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class InstrumentedTest : DatabaseTest(){

    @Test
    fun api_data_success(){

        var  currentWeatherList : ArrayList<CurrentWeather> =
            ArrayList<CurrentWeather>()

        var dummyCurrentWeather1 = CurrentWeather()
        var dummyCurrentWeather2 = CurrentWeather()
        var dummyCurrentWeather3 = CurrentWeather()


        currentWeatherList.add(dummyCurrentWeather1)
        currentWeatherList.add(dummyCurrentWeather2)
        currentWeatherList.add(dummyCurrentWeather3)

        weatherForecastDB.getCurrentWeatherDao().insertCurrentWeatherList(currentWeatherList)


        Thread.sleep(2000)

        getCurrentWeatherDao().getAllCurrentWeathers().test().assertValueCount(0)


    }


    @Test
    fun api_data_fail(){

        var  currentWeatherList : ArrayList<CurrentWeather> =
            ArrayList<CurrentWeather>()

        var dummyCurrentWeather1 =CurrentWeather()
        var dummyCurrentWeather2 = CurrentWeather()
        var dummyCurrentWeather3 = CurrentWeather()

        currentWeatherList.add(dummyCurrentWeather1)
        currentWeatherList.add(dummyCurrentWeather2)
        currentWeatherList.add(dummyCurrentWeather3)

            getCurrentWeatherDao().insertCurrentWeatherList(currentWeatherList)


            getCurrentWeatherDao().getAllCurrentWeathers().test().assertNoValues()


    }


}
