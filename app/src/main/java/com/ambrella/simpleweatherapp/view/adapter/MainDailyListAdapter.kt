package com.ambrella.simpleweatherapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.ambrella.simpleweatherapp.R
import com.ambrella.simpleweatherapp.bussness.model.DailyWatherModel
import com.ambrella.simpleweatherapp.view.*
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView

class MainDailyListAdapter: BaseAdapter<DailyWatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily,parent,false)
        return DailyViewHolder(view)
    }

    @SuppressLint("NonConstantResourceId")
    inner class DailyViewHolder(view: View):BaseViewHolder(view)
    {

        @BindView(R.id.item_daily_date_tv)
        lateinit var date:MaterialTextView
        @BindView(R.id.item_daily_pop_tv)
        lateinit var popRate:MaterialTextView
        @BindView(R.id.item_daily_max_temp_tv)
        lateinit var maxTemp:MaterialTextView
        @BindView(R.id.item_daily_min_temp_tv)
        lateinit var minTemp:MaterialTextView
        @BindView(R.id.item_daily_weather_condition_icon)
        lateinit var icon:ImageView

        init {
            ButterKnife.bind(this,itemView)
        }

        override fun bindView(position: Int) {
            mData[position].apply {
                date.text=dt.toDateFormatOf(HOUR_DOUBLE_DOT_MINUTE)+" "
                popRate.text=pop.toPercentString(" %")
                minTemp.text=StringBuilder().append(temp.min.toDegree()).append("°").toString()
                maxTemp.text=StringBuilder().append(temp.max.toDegree()).append("°").toString()
                Glide
                    .with(icon)
                    .load("https://openweathermap.org/img/wn/${weather[0].icon}.png")
                   .into(icon)
            }


        }

    }
}