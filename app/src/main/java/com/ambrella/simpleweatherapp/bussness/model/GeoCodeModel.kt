package com.ambrella.simpleweatherapp.bussness.model

data class GeoCodeModel(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String?,
    var isFavorite:Boolean=false
)