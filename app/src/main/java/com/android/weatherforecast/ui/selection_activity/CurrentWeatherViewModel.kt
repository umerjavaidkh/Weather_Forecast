/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.android.weatherforecast.ui.selection_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherforecast.app_utils.Constants
import com.android.weatherforecast.app_utils.Constants.MAX_ALLOWED_CITIES
import com.android.weatherforecast.app_utils.Constants.MIN_ALLOWED_CITIES
import com.android.weatherforecast.app_utils.State
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.data.repository.CurrentWeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [FiveDayWeatherActivity]
 */

class CurrentWeatherViewModel @Inject constructor(private var currentWeatherRepository: CurrentWeatherRepository) :
    ViewModel() {

      val disposable:CompositeDisposable =CompositeDisposable()

      var  _currentWeatherLiveDataList : MutableLiveData<State<List<CurrentWeather>>> =MutableLiveData<State<List<CurrentWeather>>>()

      var  resultList =ArrayList<CurrentWeather>()


    fun mapToList(cites:String):List<String>{

        return cites.split(",").toList()
    }

    fun  isValidDataEnteredByUser(cites:String):Boolean {

        if(!cites.isEmpty()){

            var arrayOfCities = cites.split(",")

           var invalidData= arrayOfCities.filter {
                it.trim().isEmpty()
            }

          return  invalidData.isEmpty() && arrayOfCities.size>=MIN_ALLOWED_CITIES && arrayOfCities.size <= MAX_ALLOWED_CITIES
        }

        return false
    }


    fun getWeatherData(){

        disposable.add(
            currentWeatherRepository.getCurrentWeatherFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<CurrentWeather>?>() {


                    override fun onError(e: Throwable) {

                        _currentWeatherLiveDataList.value = State.error<List<CurrentWeather>>(e.message?:"")

                    }

                    override fun onComplete() {

                    }

                    override fun onNext(t: List<CurrentWeather>) {

                        t?.let {
                            _currentWeatherLiveDataList.value = State.success(t)
                        }


                    }


                }))
    }


    fun getCurrentWeatherNew(citiesList:List<String>){

       _currentWeatherLiveDataList.value = State.loading<List<CurrentWeather>>()

        disposable.add(
            currentWeatherRepository.getCurrentWeatherRX(
                citiesList, Constants.UNITS, Constants.Lang, Constants.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<CurrentWeather>?>() {


                    override fun onError(e: Throwable) {

                        _currentWeatherLiveDataList.value = State.error<List<CurrentWeather>>(e.message?:"")
                    }

                    override fun onComplete() {


                    }

                    override fun onNext(t: List<CurrentWeather>) {

                        t?.let {

                            _currentWeatherLiveDataList.value=State.success(t)

                        }


                    }


                })
        )



    }

    fun getCurrentWeather(citiesList:String){

        viewModelScope.launch {

          resultList.clear()

            //citiesList.forEach {

               var result= async {

                    currentWeatherRepository.getCurrentWeather(citiesList,Constants.UNITS,Constants.Lang,Constants.API_KEY).collect {

                        when(it){

                            is State.Success -> {

                                it.data?.let {
                                    resultList.add(it)
                                }

                            }

                        }

                    }


               // }

               // _currentWeatherLiveDataList.value=resultList


            }

        }


    }


    override fun onCleared() {
        super.onCleared()

        disposable.dispose()
    }
}
