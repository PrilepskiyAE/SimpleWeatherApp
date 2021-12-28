package com.ambrella.simpleweatherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.simpleweatherapp.R

class MainDailyListAdapter: RecyclerView.Adapter<MainDailyListAdapter.DailyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily,parent,false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =10



    inner class DailyViewHolder(view: View):RecyclerView.ViewHolder(view)
    {

    }
}