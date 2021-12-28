package com.ambrella.simpleweatherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.simpleweatherapp.R

class MainHourlyListAdapter:RecyclerView.Adapter<MainHourlyListAdapter.HourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly,parent,false)
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 10

    inner class HourViewHolder(view:View):RecyclerView.ViewHolder(view){

    }


}