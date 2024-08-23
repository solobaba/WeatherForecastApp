package com.solomonoduniyi.weatherforecastapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.solomonoduniyi.weatherforecastapp.databinding.ActivityMainBinding
import com.solomonoduniyi.weatherforecastapp.presentation.viewmodel.WeatherForecastViewmodel
import com.solomonoduniyi.weatherforecastapp.utils.extension.GpsTracker
import com.solomonoduniyi.weatherforecastapp.utils.extension.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: WeatherForecastViewmodel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var gpsTracker: GpsTracker? = null
    var latitude = ""
    var longitude = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUserLocation()
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
            latitude = gpsTracker?.userLatitude.toString()
            longitude = gpsTracker?.userLongitude.toString()
            shortToast("Latitude $latitude")
            shortToast("Longtitude $longitude")
        } else {
            gpsTracker?.showSettingsAlert()
        }
    }
}