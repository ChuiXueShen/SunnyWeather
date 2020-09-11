package com.demo.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.demo.sunnyweather.logic.dao.PlaceDao
import com.demo.sunnyweather.logic.model.Place
import com.demo.sunnyweather.logic.model.Weather
import com.demo.sunnyweather.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.http.Query
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 * @package:    com.demo.sunnyweather.logic
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 14:12
 */
object Repository {
    val TAG = "Repository"

    //保存城市
    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    //获取保存的城市
    fun getSavedPlace(): Place = PlaceDao.getSavedPlace()
    //判断城市是否被保存
    fun isPlaceSaved(): Boolean = PlaceDao.isPlaceSaved()

    /**
     * 搜索城市
     * @query：搜索词
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


    /**
     * 获取天气
     * @lng： 经度
     * @lat： 纬度
     */
    fun refreshWeather(
        lng: String, lat: String
    ): LiveData<Result<Weather>> = fire<Weather>(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async { SunnyWeatherNetWork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { SunnyWeatherNetWork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            //判断数据合法性
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(dailyResponse.result.daily, realtimeResponse.result.realtime)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status},daily response status is ${dailyResponse.status}"))
            }
        }
    }

    //对相同的步骤进行封装简化
    private fun <T> fire(
        context: CoroutineContext,
        block: suspend () -> Result<T>
    ): LiveData<Result<T>> = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }


}