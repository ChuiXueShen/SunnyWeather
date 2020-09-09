package com.demo.sunnyweather.logic

import androidx.lifecycle.liveData
import com.demo.sunnyweather.logic.model.Place
import com.demo.sunnyweather.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Query
import java.lang.Exception
import java.lang.RuntimeException

/**
 * @package:    com.demo.sunnyweather.logic
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 14:12
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }



}