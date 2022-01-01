package com.ambrella.simpleweatherapp.view

import com.ambrella.simpleweatherapp.bussness.model.DailyWatherModel
import com.ambrella.simpleweatherapp.bussness.model.HourlyWeatherModel
import com.ambrella.simpleweatherapp.bussness.model.WeatherData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView:MvpView {
    @AddToEndSingle
    fun displayLocation(data:String)
    @AddToEndSingle
    fun displayCurrentData(data: WeatherData)
    @AddToEndSingle
    fun displayHourlyData(data:List<HourlyWeatherModel>)
    @AddToEndSingle
    fun displayDilyData(data:List<DailyWatherModel>)
    @AddToEndSingle
    fun displayError(error: Throwable)
    @AddToEndSingle
    fun setLoading(flag: Boolean)

}