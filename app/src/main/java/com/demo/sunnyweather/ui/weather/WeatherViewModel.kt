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

    private val locationLiveData = MutableLiveData<Location>()//坐标LiveData对象

    var locationLng = ""//经度
    var locationLat = ""//纬度
    var placeName = "" //地名

    //对坐标LiveData对象进行观察如果有变化则去通过仓库层获取数据 最终返回一个 数据LiveData对象
    val weatherLiveData = Transformations.switchMap(locationLiveData) { it ->
        Repository.refreshWeather(it.lng, it.lat)
    }

    //用于改变坐标LiveData对象
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)

    }
}