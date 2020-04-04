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

package com.android.weatherforecast.data.repository

import com.android.weatherforecast.app_utils.State
import com.android.weatherforecast.data.WeatherService
import com.android.weatherforecast.data.local.dao.FiveDaysWeatherDao
import com.android.weatherforecast.models.FiveDaysWeather
import dev.shreyaspatil.foodium.data.repository.NetworkBoundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */

@Singleton
class FiveDaysWeatherRepository @Inject constructor(
    private val fiveDaysWeatherDao: FiveDaysWeatherDao,
    private val weatherService: WeatherService
) {




    /**
     * Fetched the FiveDaysWeather from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getFiveDaysWeather( id: Int,
                            q: String?,
                            lang:String?,
                           units: String?,
                           lat: String?,
                           lon: String?,
                           appId: String?): Flow<State<FiveDaysWeather>> {
        return object : NetworkBoundRepository<FiveDaysWeather, FiveDaysWeather>() {

            override suspend fun saveRemoteData(response: FiveDaysWeather) {

                synchronized(fiveDaysWeatherDao){
                    fiveDaysWeatherDao.deleteAllFiveDaysWeather()
                    response.id=id
                    fiveDaysWeatherDao.insertFiveDaysWeather(response)
                }

        }

            override fun fetchFromLocal(): Flow<FiveDaysWeather> = fiveDaysWeatherDao.getAllFiveDaysWeather(id)

            override suspend fun fetchFromRemote(): Response<FiveDaysWeather?>? = weatherService.getFiveDaysWeather(q,lang,units,lat,lon,appId)

        }.asFlow().flowOn(Dispatchers.IO)
    }

}
