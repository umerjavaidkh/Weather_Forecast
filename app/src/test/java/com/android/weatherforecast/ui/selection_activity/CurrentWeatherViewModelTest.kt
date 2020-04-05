package com.android.weatherforecast.ui.selection_activity



import androidx.lifecycle.Observer
import com.android.weatherforecast.app_utils.Constants

import com.android.weatherforecast.app_utils.State

import com.android.weatherforecast.data.repository.CurrentWeatherRepository
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.util.InstantExecutorExtension
import com.android.weatherforecast.util.RxSchedulerExtensionForJunit5

import io.reactivex.Observable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension


import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception


@ExtendWith(InstantExecutorExtension::class)
public class CurrentViewModelViewModelTest {


    @Mock
    lateinit var currentWeatherRepository: CurrentWeatherRepository

    lateinit var currentWeatherViewModel: CurrentWeatherViewModel



    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @RegisterExtension
        @JvmField
        val schedulers = RxSchedulerExtensionForJunit5()
    }


    @Mock
    lateinit var observer: Observer<State<List<CurrentWeather>>>

    //Mockito.spy returns real object
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
    fun is_user_entered_valid_data_for_cities_true() {

        var cities="dubai,sharjah,ajman"

        var returnValue=currentWeatherViewModel.isValidDataEnteredByUser(cities)

        assertEquals(true, returnValue)
    }

    @Test
    fun is_user_entered_valid_data_for_cities_false(){

        var cities="dubai,sharjah,ajman,,,  "

        var returnValue=currentWeatherViewModel.isValidDataEnteredByUser(cities)

        assertNotEquals(true, returnValue)
    }


    @Test
    fun is_map_to_cities_list_from_string_true(){

        var cities="dubai,sharjah,ajman"

        var list= mutableListOf("dubai","sharjah","ajman")

        var returnValue=currentWeatherViewModel.mapToList(cities)

        assertEquals(list[1], returnValue[1])
    }

    @Test
    fun is_map_to_cities_list_from_string_false(){

        var cities="dubai,sharjah,ajman"

        var list= mutableListOf("dubai","sharjah","ajman")

        var returnValue=currentWeatherViewModel.mapToList(cities)

        assertNotEquals(list[0], returnValue[1])
    }


    @Test
    fun get_Weather_from_db() {

        var  currentWeatherList : ArrayList<CurrentWeather> =
            ArrayList<CurrentWeather>()

        var dummyCurrentWeather1 = Mockito.mock(CurrentWeather::class.java)
        var dummyCurrentWeather2 = Mockito.mock(CurrentWeather::class.java)
        var dummyCurrentWeather3 = Mockito.mock(CurrentWeather::class.java)

        currentWeatherList.add(dummyCurrentWeather1)
        currentWeatherList.add(dummyCurrentWeather2)
        currentWeatherList.add(dummyCurrentWeather3)

        Mockito.`when`(currentWeatherRepository.getCurrentWeatherFromDB())
            .thenReturn(Observable.just(currentWeatherList))

        currentWeatherViewModel._currentWeatherLiveDataList.observeForever(observer)
        currentWeatherViewModel.getWeatherData()
        Thread.sleep(1000)
        assert(currentWeatherViewModel._currentWeatherLiveDataList.value == State.success(currentWeatherList))

    }


    @Test
    fun get_Weather_from_remote() {

        var  currentWeatherList : ArrayList<CurrentWeather> =
            ArrayList<CurrentWeather>()

        var dummyCurrentWeather1 = Mockito.mock(CurrentWeather::class.java)
        var dummyCurrentWeather2 = Mockito.mock(CurrentWeather::class.java)
        var dummyCurrentWeather3 = Mockito.mock(CurrentWeather::class.java)

        currentWeatherList.add(dummyCurrentWeather1)
        currentWeatherList.add(dummyCurrentWeather2)
        currentWeatherList.add(dummyCurrentWeather3)


        //params  for getCurrentWeatherRX

        val citiesList= listOf<String>("Dubai","Sharjah","Ajman")

        Mockito.`when`(currentWeatherRepository.getCurrentWeatherRX(citiesList,Constants.UNITS,Constants.Lang,Constants.API_KEY))
            .thenReturn(Observable.just(currentWeatherList))

        currentWeatherViewModel._currentWeatherLiveDataList.observeForever(observer)
        currentWeatherViewModel.getCurrentWeatherNew(citiesList)
        Thread.sleep(1000)
        assert(currentWeatherViewModel._currentWeatherLiveDataList.value == State.success(currentWeatherList))

    }


}