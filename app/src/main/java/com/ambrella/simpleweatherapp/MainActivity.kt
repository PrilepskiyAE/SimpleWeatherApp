package com.ambrella.simpleweatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager

import android.location.Location
import com.google.android.gms.location.LocationRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ambrella.simpleweatherapp.bussness.model.DailyWatherModel
import com.ambrella.simpleweatherapp.bussness.model.HourlyWeatherModel
import com.ambrella.simpleweatherapp.bussness.model.WeatherData
import com.ambrella.simpleweatherapp.presenters.MainPresenter
import com.ambrella.simpleweatherapp.view.MainView
import com.ambrella.simpleweatherapp.view.adapter.MainDailyListAdapter
import com.ambrella.simpleweatherapp.view.adapter.MainHourlyListAdapter
import com.google.android.gms.location.LocationRequest.*
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import moxy.MvpAppCompatActivity

import moxy.ktx.moxyPresenter


const val TAG="GEO_TEST"
class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy{
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val locationRequest by lazy {
        initLocationRequest()
    }

    private lateinit var  mLocation: Location

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   checkPermissions()
       initViews()
        main_hourly_list.apply {
    adapter= MainHourlyListAdapter()
    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    setHasFixedSize(true)
}
        main_daily_list.apply {
            adapter=MainDailyListAdapter()
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }

        mainPresenter.enable()
        geoService.requestLocationUpdates(locationRequest,geoCallback,null)
    }
    @SuppressLint("SetTextI18n")
    fun initViews()
    {
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


    //--moxy
    override fun displayLocation(data: String) {
        main_city_name_tv.text=data
    }

    override fun displayCurrentData(data: WeatherData) {
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

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (main_hourly_list.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDilyData(data: List<DailyWatherModel>) {
        (main_daily_list.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }
    //--moxy
    //--initial activity code


    //---------location code



   fun initLocationRequest():LocationRequest{

        val request=LocationRequest.create()
        return request.apply {
            interval=10000
           fastestInterval=5000
            priority=LocationRequest.PRIORITY_HIGH_ACCURACY

        }

    }


    private val geoCallback=object : LocationCallback(){
        override fun onLocationResult(geo: LocationResult) {
            Log.d(TAG, "onLocationResult: ${geo.locations.size}")
            for (location in geo.locations)
            {
                mLocation=location
                mainPresenter.refresh(mLocation.latitude.toString(),mLocation.longitude.toString())
                Log.d(TAG, "onLocationResult: lat: ${location.latitude};lon: ${location.longitude}; ")
            }
        }

    }





    //---------location code
}