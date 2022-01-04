package com.ambrella.simpleweatherapp.bussness.repos

import com.ambrella.simpleweatherapp.bussness.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepository<T>(val api:ApiProvider) {
     val dataEmitter:BehaviorSubject<T> = BehaviorSubject.create()

}