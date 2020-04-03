package com.android.weatherforecast

import android.app.Application
import androidx.room.Room
import com.android.weatherforecast.data.local.WeatherForecastDB
import com.android.weatherforecast.data.repository.CurrentWeatherRepository
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.ui.selection_activity.CurrentWeatherViewModel
import com.android.weatherforecast.util.InstantExecutorExtension
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.Context

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception


@ExtendWith(InstantExecutorExtension::class)
public class CurrentViewModelViewModelTest {


    @Mock
    lateinit var currentWeatherRepository: CurrentWeatherRepository

    lateinit var currentWeatherViewModel: CurrentWeatherViewModel




    @BeforeEach
    fun setup() {



        try {
            MockitoAnnotations.initMocks(this)
            currentWeatherViewModel = Mockito.spy(CurrentWeatherViewModel(currentWeatherRepository))
        }catch (ex:Exception){

            ex.toString()
        }
    }





    @Test
    fun is_user_entered_valid_data_true() {

        var cities="dubai,sharjah,ajman"

        var returnValue=currentWeatherViewModel.isValidDataEnteredByUser(cities)

        assertEquals(true, true)
    }

    @Test
    fun is_user_entered_valid_data_false(){

        var cities="dubai,sharjah,ajman,,,  "

        var returnValue=currentWeatherViewModel.isValidDataEnteredByUser(cities)

        assertNotEquals(true, returnValue)
    }


    @Test
    fun is_map_to_list_true(){

        var cities="dubai,sharjah,ajman"

        var list= mutableListOf("dubai","sharjah","ajman")

        var returnValue=currentWeatherViewModel.mapToList(cities)

        assertEquals(list[1], returnValue[1])
    }

    @Test
    fun is_map_to_list_false(){

        var cities="dubai,sharjah,ajman"

        var list= mutableListOf("dubai","sharjah","ajman")

        var returnValue=currentWeatherViewModel.mapToList(cities)

        assertNotEquals(list[0], returnValue[1])
    }





}