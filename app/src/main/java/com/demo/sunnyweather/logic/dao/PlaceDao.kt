package com.demo.sunnyweather.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.demo.sunnyweather.SunnyWeatherApplication
import com.demo.sunnyweather.logic.model.Place
import com.google.gson.Gson

/**
 * @package:    com.demo.sunnyweather.logic.dao
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/11 11:37
 */
object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeInfo = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeInfo, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}