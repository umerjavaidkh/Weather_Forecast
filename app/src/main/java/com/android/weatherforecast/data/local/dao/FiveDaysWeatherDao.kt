package com.android.weatherforecast.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.android.weatherforecast.models.FiveDaysWeather


@Dao
interface FiveDaysWeatherDao {

    /**
     * Insert [FiveDaysWeather] into the [FiveDaysWeather.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param FiveDaysWeather FiveDaysWeather
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFiveDaysWeather(FiveDaysWeather: FiveDaysWeather)

    /**
     * Deletes all the FiveDaysWeather from the [FiveDaysWeather.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${FiveDaysWeather.TABLE_NAME}")
    fun deleteAllFiveDaysWeather()


    /**
     * Fetches all the data from the [FiveDaysWeather.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${FiveDaysWeather.TABLE_NAME}")
    fun getAllFiveDaysWeather(): Flow<FiveDaysWeather>
}