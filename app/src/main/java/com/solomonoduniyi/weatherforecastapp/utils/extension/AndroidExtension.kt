package com.solomonoduniyi.weatherforecastapp.utils.extension

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun <T> runOnMain(block: () -> T) = GlobalScope.launch(Dispatchers.Main) {
    block()
}

fun logTrace(text: String) =  Log.d("WeatherForecast", text)

fun Fragment.showSnack(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view!!, message, duration).show()

//fun Context.showSnack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
//    runOnMain {
//        Snackbar.make(this, message, duration).show()
//    }
//}

fun Context.shortToast(msg: String) {
    runOnMain {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}