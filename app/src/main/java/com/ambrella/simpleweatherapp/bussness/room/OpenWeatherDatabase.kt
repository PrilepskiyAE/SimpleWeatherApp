package com.ambrella.simpleweatherapp.bussness.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [WeatherDataEntity::class], exportSchema = false, version = 1)
abstract class OpenWeatherDatabase : RoomDatabase(){

    abstract fun getWeatherDao(): WeatherDAO

}