package com.ambrella.simpleweatherapp

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.ambrella.simpleweatherapp.bussness.room.OpenWeatherDatabase
import java.security.AccessControlContext
import java.security.AccessController.getContext

const val APP_SETTINGS="App settings"
const val IS_STARTED_UP="Is started up"

class App:Application() {

val context: AccessControlContext? = getContext()
    companion object{
         lateinit var db:OpenWeatherDatabase





    }

    override fun onCreate() {
        super.onCreate()


                db=Room.databaseBuilder(this,OpenWeatherDatabase::class.java,"OpenWeatherDB")
                .fallbackToDestructiveMigration()
                .build()





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