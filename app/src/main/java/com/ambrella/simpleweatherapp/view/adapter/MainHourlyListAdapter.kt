package com.ambrella.simpleweatherapp.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.ambrella.simpleweatherapp.R
import com.ambrella.simpleweatherapp.bussness.model.HourlyWeatherModel
import com.ambrella.simpleweatherapp.view.*
import com.ambrella.simpleweatherapp.view.DAY_WEEK_NAME_LONG
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_main.*

const val TAG="RV_TEST"
class MainHourlyListAdapter:BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        Log.d(TAG,"----onCreateViewHolder: ")
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly,parent,false)
        return HourViewHolder(view)
    }


    @SuppressLint("NonConstantResourceId")
    inner class HourViewHolder(view:View):BaseViewHolder(view){

        @BindView(R.id.item_hourly_time_tv)
        lateinit var time: MaterialTextView

        @BindView(R.id.item_hourly_temp_tv)
        lateinit var temperature: MaterialTextView
        @BindView(R.id.item_hourly_pop_tv)
        lateinit var popRate: MaterialTextView
        @BindView(R.id.item_hourly_weather_condition_icon)
        lateinit var icon: ImageView

        init {
            ButterKnife.bind(this,itemView)
        }

        override fun bindView(position: Int) {
            mData[position].apply {
                time.text=dt.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)+" "
                temperature.text=StringBuilder().append(temp.toDegree()).append("°").toString()
                popRate.text=pop.toPercentString(" %")

                Glide
                    .with(icon)
                    .load("https://openweathermap.org/img/wn/${weather[0].icon}.png")
                    .into(icon)
               // icon.setImageResource(R.drawable.ic_outline_wb_sunny_24)
            }

        }
    }


}