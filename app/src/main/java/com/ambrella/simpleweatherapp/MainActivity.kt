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
import com.ambrella.simpleweatherapp.view.adapter.MainDailyListAdapter
import com.ambrella.simpleweatherapp.view.adapter.MainHourlyListAdapter
import com.google.android.gms.location.LocationRequest.*
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

const val GEO_LOCATION_REQUEST_COD_SUCCESS=1
const val TAG="GEO_TEST"
class MainActivity : AppCompatActivity() {
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
        checkPermissions()
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
                Log.d(TAG, "onLocationResult: lat: ${location.latitude};lon: ${location.longitude}; ")
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionsResult: $requestCode")
    }

    private fun checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Нам нужны гео данные")
                    .setMessage("Пж разрешите доступ к  гео данным")
                    .setPositiveButton("ok"){_,_ ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),GEO_LOCATION_REQUEST_COD_SUCCESS)
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),GEO_LOCATION_REQUEST_COD_SUCCESS)
                    }
                    .setNegativeButton("Cancel"){
                        dialog,_ ->
                        dialog.dismiss()

                    }
                    .create()
                    .show()
        }
    }

    //---------location code
}