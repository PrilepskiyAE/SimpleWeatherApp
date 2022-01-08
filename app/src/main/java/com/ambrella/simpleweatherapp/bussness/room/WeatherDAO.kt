package com.ambrella.simpleweatherapp.bussness.room

import androidx.room.*

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM weatherdata WHERE id = 1")
    fun getWeatherData():WeatherDataEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertWeatherData(data:WeatherDataEntity)
    @Update
    fun updateWeatherData(data: WeatherDataEntity)
    @Delete
    fun deleteWeatherData(data: WeatherDataEntity)
}