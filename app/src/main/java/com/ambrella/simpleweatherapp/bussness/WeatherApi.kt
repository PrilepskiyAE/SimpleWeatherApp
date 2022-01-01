package com.ambrella.simpleweatherapp.bussness

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/onecall")
    fun getWeatherForecast(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("exclude") exclude:String="minutely,alerts",
        @Query("appid")appid:String="a356083d281fd41b8cc084604cfea1ab",
        @Query("lang")lang:String="en"
    )
}