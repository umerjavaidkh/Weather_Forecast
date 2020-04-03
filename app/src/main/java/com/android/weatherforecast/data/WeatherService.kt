package com.android.weatherforecast.data


import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.models.FiveDaysWeather
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatherService {


    /**
     * Get five days weather forecast.
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of [FiveDaysWeather]
     */
    @GET("forecast")
    suspend fun getFiveDaysWeather(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("lang") lang: String?,
        @Query("cnt")  dayCount :Int?,
        @Query("appid") appId: String?
    ): Response<FiveDaysWeather?>?


    /**
     * Get five days weather forecast.
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lat  String lat coordinate
     * @param lon  String lon coordinate
     * @param appId String api key
     * @return instance of [FiveDaysWeather]
     */

    @GET("forecast")
    suspend fun getFiveDaysWeather(
        @Query("q") q: String?,
        @Query("lang") lang: String?,
        @Query("units") units: String?,
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appId: String?
    ): Response<FiveDaysWeather?>?

    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of [CurrentWeather]
     */


    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("lang")  lang: String?,
        @Query("appid") appId: String?
    ): Response<CurrentWeather?>?

    @GET("weather")
     fun getCurrentWeatherNew(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("lang")  lang: String?,
        @Query("appid") appId: String?
    ): Observable<CurrentWeather?>


}