package com.solomonoduniyi.weatherforecastapp.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solomonoduniyi.weatherforecastapp.R
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherListDomain
import com.solomonoduniyi.weatherforecastapp.utils.extension.getDateTime
import java.time.format.TextStyle
import java.util.Locale

class WeatherForecastAdapter(
    val context: Context,
    private val onWeatherListListener: OnWeatherListListener
) : RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    private var weatherListData: List<WeatherListDomain> = listOf()

    interface OnWeatherListListener {
        fun onWeatherListener(weatherListData: WeatherListDomain)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherForecastAdapter.ViewHolder {
        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return if (weatherListData.isEmpty()) {
            0
        } else {
            weatherListData.size
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTransaction(weatherListDomain: List<WeatherListDomain>?) {
        if (weatherListDomain != null) {
            weatherListData = weatherListDomain
        }
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherForecastAdapter.ViewHolder, position: Int) {
        val weatherDataList = weatherListData[position]
        holder.timeOfDay.text =
            weatherDataList.dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
        holder.dayOfWeek.text = weatherDataList.dt?.let {
            getDateTime(it)?.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            )
        }
        holder.temperature.text = weatherDataList.main?.temp.toString().substringBefore(".") + "°"
        holder.minimumTemp.text =
            weatherDataList.main?.tempMin.toString().substringBefore(".") + "°"
        holder.maximumTemp.text =
            weatherDataList.main?.tempMax.toString().substringBefore(".") + "°"
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val timeOfDay: TextView = view.findViewById(R.id.textViewTimeOfDay)
        val dayOfWeek: TextView = view.findViewById(R.id.textViewDayOfWeek)
        val temperature: TextView = view.findViewById(R.id.textViewTemp)
        val minimumTemp: TextView = view.findViewById(R.id.tempMinText)
        val maximumTemp: TextView = view.findViewById(R.id.tempMaxText)

        override fun onClick(p0: View?) {
            onWeatherListListener.onWeatherListener(weatherListData[adapterPosition])
        }

        init {
            view.setOnClickListener(this)
        }
    }
}