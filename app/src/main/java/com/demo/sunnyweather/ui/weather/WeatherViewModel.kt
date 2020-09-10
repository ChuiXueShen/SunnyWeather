package com.demo.sunnyweather.ui.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.sunnyweather.logic.Repository
import com.demo.sunnyweather.logic.model.Location

/**
 * @package:    com.demo.sunnyweather.ui.weather
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/10 13:27
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""//经度
    var locationLat = ""//纬度
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData) { it ->

        Repository.refreshWeather(it.lng, it.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        Log.d("PlaceFragmentre",  "${lat}  ${lng}");
        locationLiveData.value = Location(lng, lat)
        Log.d("PlaceFragmentweather",  locationLiveData.value.toString());
    }
}