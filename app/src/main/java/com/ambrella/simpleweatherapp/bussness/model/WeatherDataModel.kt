package com.ambrella.simpleweatherapp.bussness.model

data class WeatherDataModel(
    val current: Current,
    val daily: List<DailyWatherModel>,
    val hourly: List<HourlyWeatherModel>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)