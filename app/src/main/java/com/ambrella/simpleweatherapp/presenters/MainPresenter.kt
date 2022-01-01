package com.ambrella.simpleweatherapp.presenters

import com.ambrella.simpleweatherapp.view.MainView

class MainPresenter :BasePresenter<MainView>(){
    override fun enable() {

    }
    fun refresh(lat:String,lon:String){
        viewState.setLoading(true)

    }

}