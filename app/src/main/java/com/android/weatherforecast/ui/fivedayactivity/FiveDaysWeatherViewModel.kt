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

package com.android.weatherforecast.ui.fivedayactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherforecast.app_utils.State
import com.android.weatherforecast.models.FiveDaysWeather
import com.android.weatherforecast.data.repository.FiveDaysWeatherRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [FiveDayWeatherActivity]
 */

class FiveDaysWeatherViewModel @Inject constructor(private val fiveDaysWeatherRepository: FiveDaysWeatherRepository) :
    ViewModel() {

    private val _fiveDaysWeatherLiveData = MutableLiveData<State<FiveDaysWeather>>()

    val postsLiveData: LiveData<State<FiveDaysWeather>>
        get() = _fiveDaysWeatherLiveData



    fun getFiveDaysWeatherWithPos(
        id:Int,
        q: String?,
        lang: String?,
        units: String?,
        lat: String?,
        lon: String?,
        appId: String?
    ){
        viewModelScope.launch {
            fiveDaysWeatherRepository.getFiveDaysWeather(id,q,lang,units,lat,lon,appId).collect {
                _fiveDaysWeatherLiveData.value = it
            }
        }
    }

}
