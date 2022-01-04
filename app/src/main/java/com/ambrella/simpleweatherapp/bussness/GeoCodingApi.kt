package com.ambrella.simpleweatherapp.bussness

import com.ambrella.simpleweatherapp.bussness.model.GeoCodeModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {
    @GET("geo/1.0/reverse?")
    fun getCityByCoord(
    @Query("lat") lat :String,
    @Query("lon") lon :String,
    @Query("limit") limit:String="10",
    @Query("appid") id:String="a356083d281fd41b8cc084604cfea1ab"
    ): Observable<List<GeoCodeModel>>

}