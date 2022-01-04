package com.ambrella.simpleweatherapp.view

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val DAY_FULL_MONTH_NAME="dd MMMM"
const val DAY_WEEK_NAME_LONG ="dd EEEE"
const val HOUR_DOUBLE_DOT_MINUTE="HH:mm"

fun Long.toDateFormatOf(format:String):String
{
    val cal = Calendar.getInstance()
    val timeZone=cal.timeZone
    val sdf =SimpleDateFormat(format,Locale.getDefault())
    sdf.timeZone=timeZone
    return sdf.format(Date(this*1000))
}

fun Double.toDegree()=(this-273.15).roundToInt().toString()

fun Double.toPercentString(extraPart:String="")=(this*100).roundToInt().toString()+extraPart

