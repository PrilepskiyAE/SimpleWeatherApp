package com.ambrella.simpleweatherapp

import android.app.Application
import android.content.Intent

const val APP_SETTINGS="App settings"
const val IS_STARTED_UP="Is started up"

class App:Application() {

    //TODO Переменные БД

    override fun onCreate() {
        super.onCreate()

            //TODO init DB

        val preferences= getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)
        val flag=preferences.contains(IS_STARTED_UP)
        if (!flag)
        {
            val editor=preferences.edit()
            editor.putBoolean(IS_STARTED_UP,true)
            editor.apply()
           val intent= Intent(this,InitialActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}