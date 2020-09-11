package com.demo.sunnyweather.logic.network

import com.demo.sunnyweather.logic.model.DailyResponse
import com.demo.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @package:    com.demo.sunnyweather.logic.network
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 13:47
 */
object SunnyWeatherNetWork {
    private val placeService = ServiceCreator.create<PlaceService>()
    private val weatherService = ServiceCreator.create<WeatherService>()
    suspend fun getDailyWeather(lng: String, lat: String): DailyResponse =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String, lat: String): RealtimeResponse =
        weatherService.getRealtimeWeather(lng, lat).await()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }
            })
        }
    }
}