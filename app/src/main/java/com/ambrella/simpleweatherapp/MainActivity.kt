package com.ambrella.simpleweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_city_name_tv.text="Moscow"
        main_date_tv.text="31 decabr"
        main_weather_condition_icon.setImageResource(R.drawable.ic_outline_wb_sunny_24)
        main_temp.text="25\u00B0"
        main_temp_min_tv.text="19"
        main_temp_max_tv.text="29"
        main_weather_image.setImageResource(R.mipmap.could3x)
        main_pressure_mu_tv.text="1023 hPA"
        main_humidity_mu_tv.text="28%"
        main_wind_speed_mu_tv.text="5 m/s"
        main_sumrise_mu_tv.text="4:30"
        main_sumset_mu_tv.text="22:00"
    }
}