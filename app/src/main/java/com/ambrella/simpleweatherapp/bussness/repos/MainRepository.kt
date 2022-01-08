package com.ambrella.simpleweatherapp.bussness.repos

import android.util.Log
import com.ambrella.simpleweatherapp.bussness.ApiProvider
import com.ambrella.simpleweatherapp.bussness.model.WeatherDataModel
import com.ambrella.simpleweatherapp.bussness.room.WeatherDataEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import com.google.gson.Gson
import java.util.concurrent.TimeUnit

const val TAG="MainRepo"
class MainRepository(api: ApiProvider):BaseRepository<MainRepository.ServerResponse>(api) {
    var sec:Long=1
    private val gson = Gson()
    private val dbAccess=db.getWeatherDao()
    fun reloadData(lat:String,lon:String){

    Observable.

    zip(
        api.providerWeatherApi().getWeatherForecast(lat,lon),
        api.providerGeoCodeApi().getCityByCoord(lat,lon).map {
           it.asSequence()
               .map {model -> model.name}
               .toList()
               .filterNotNull()
               .first()
        }
            ,
        { weatherData, geoCode ->
            ServerResponse(geoCode,weatherData)
        }
    )

        .subscribeOn(Schedulers.io())
        .doOnNext { dbAccess.insertWeatherData(WeatherDataEntity(data = gson.toJson(it.weatherData), city = it.cityName)) }
        .onErrorResumeNext {
            Observable.just(ServerResponse(
                dbAccess.getWeatherData().city,
                gson.fromJson(dbAccess.getWeatherData().data,WeatherDataModel::class.java),
                it
            ))
        }
        .delay(sec, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
                dataEmitter.onNext(it)
            },{
                Log.d(TAG, "reloadData: $it")
            })
        sec=7200000
    }

    data class ServerResponse(val cityName: String,val weatherData: WeatherDataModel, val error: Throwable?=null )
}