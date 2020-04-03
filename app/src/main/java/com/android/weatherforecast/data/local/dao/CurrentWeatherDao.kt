package com.android.weatherforecast.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.android.weatherforecast.models.CurrentWeather
import io.reactivex.Observable


@Dao
interface CurrentWeatherDao {

    /**
     * Insert [CurrentWeather] into the [CurrentWeather.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param currentWeather CurrentWeather
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeather: CurrentWeather)


    /**
     * Insert [CurrentWeather] into the [CurrentWeather.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param currentWeather CurrentWeather
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeatherList(currentWeatherList: List<CurrentWeather>)

    /**
     * Deletes all the CurrentWeather from the [CurrentWeather.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${CurrentWeather.TABLE_NAME}")
    fun deleteAllCurrentWeather()


    /**
     * Fetches all the data from the [CurrentWeather.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${CurrentWeather.TABLE_NAME}")
    fun getAllCurrentWeather(): Flow<CurrentWeather>

    /**
     * Fetches all the data from the [CurrentWeather.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${CurrentWeather.TABLE_NAME}")
    fun getAllCurrentWeathers(): Observable<List<CurrentWeather>>


}