package com.demo.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
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
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async { SunnyWeatherNetWork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { SunnyWeatherNetWork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            Log.d(TAG, "${realtimeResponse.status}${dailyResponse.status}");
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(dailyResponse.result.daily, realtimeResponse.result.realtime)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status},daily response status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(
        context: CoroutineContext,
        block: suspend () -> Result<T>
    ) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }

}