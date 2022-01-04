package com.ambrella.simpleweatherapp.bussness.repos

import android.util.Log
import com.ambrella.simpleweatherapp.bussness.ApiProvider
import com.ambrella.simpleweatherapp.bussness.model.WeatherDataModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

const val TAG="MainRepo"
class MainRepository(api: ApiProvider):BaseRepository<MainRepository.ServerResponse>(api) {

    fun reloadData(lat:String,lon:String){
    Observable.zip(
        api.providerWeatherApi().getWeatherForecast(lat,lon),
        api.providerGeoCodeApi().getCityByCoord(lat,lon).map {
           it.asSequence()
               .map {model -> model.name}
               .toList()
               .filterNotNull()
               .first()
        },
        { weatherData, geoCode ->
            ServerResponse(geoCode,weatherData)
        }
    )
        .subscribeOn(Schedulers.io())
        .doOnNext { /* TODO добовление в базу данных */ }
        /*.onErrorResumeNext { TODO Тут будет извлечение объекта из базы данных }*/
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
                dataEmitter.onNext(it)
            },{
                Log.d(TAG, "reloadData: $it")
            })
    }

    data class ServerResponse(val cityName: String,val weatherData: WeatherDataModel, val error: Throwable?=null )
}