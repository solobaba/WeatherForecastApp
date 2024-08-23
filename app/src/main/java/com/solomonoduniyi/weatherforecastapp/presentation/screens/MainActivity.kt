package com.solomonoduniyi.weatherforecastapp.presentation.screens

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.solomonoduniyi.weatherforecastapp.R
import com.solomonoduniyi.weatherforecastapp.databinding.ActivityMainBinding
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherListDomain
import com.solomonoduniyi.weatherforecastapp.domain.model.WeatherLocationResponseDomain
import com.solomonoduniyi.weatherforecastapp.presentation.adapter.WeatherForecastAdapter
import com.solomonoduniyi.weatherforecastapp.presentation.viewmodel.WeatherForecastViewmodel
import com.solomonoduniyi.weatherforecastapp.utils.extension.GpsTracker
import com.solomonoduniyi.weatherforecastapp.utils.extension.logTrace
import com.solomonoduniyi.weatherforecastapp.utils.extension.shortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: WeatherForecastViewmodel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherForecastAdapter: WeatherForecastAdapter
    private var layoutManager: LinearLayoutManager? = null
    private var gpsTracker: GpsTracker? = null
    var latitude: Double? = 0.0
    var longitude: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerForecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        weatherForecastAdapter = WeatherForecastAdapter(this,
            object : WeatherForecastAdapter.OnWeatherListListener {
                override fun onWeatherListener(weatherListData: WeatherListDomain) {
                    shortToast("Getting here")
                }
            })
        binding.recyclerForecast.adapter = weatherForecastAdapter

//        layoutManager = LinearLayoutManager(this)
//        binding.recyclerForecast.apply {
//            itemAnimator = null
//            adapter = weatherForecastAdapter
//        }

        getUserLocation()

        initView()
    }

    private fun getUserLocation() {
        try {
            (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        gpsTracker = GpsTracker(this)
        if (gpsTracker?.canGetLocation() == true) {
            latitude = gpsTracker?.userLatitude
            longitude = gpsTracker?.userLongitude
            shortToast("Latitude $latitude")
            shortToast("Longtitude $longitude")
        } else {
            gpsTracker?.showSettingsAlert()
        }
    }

    private fun initView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.fetchWeather(latitude ?: 0.0, longitude ?: 0.0)
                viewmodel.weatherData.collectLatest { response ->
                    response?.list.let { weatherList ->
                        weatherForecastAdapter.setTransaction(weatherList)
                    }

                    setCurrentWeather(response)
                    logTrace("WeatherForecast $response")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentWeather(response: WeatherLocationResponseDomain?) {
        var position = 0
        val city = response?.city?.name
        val country = response?.city?.country
        binding.cityName.text = "$city, $country"
        binding.textViewTemperature.text =
            response?.list?.get(position)?.main?.temp.toString().substringBefore(".") + "°"
        binding.textViewWeatherMain.text =
            response?.list?.get(position)?.weather?.get(position)?.main
        binding.textViewHumidity.text =
            response?.list?.get(position)?.main?.humidity.toString() + "°"
    }
}