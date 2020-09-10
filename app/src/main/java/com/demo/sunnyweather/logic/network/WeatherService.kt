package com.demo.sunnyweather.logic.network

import com.demo.sunnyweather.SunnyWeatherApplication
import com.demo.sunnyweather.logic.model.DailyResponse
import com.demo.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @package:    com.demo.sunnyweather.logic.network
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/10 10:29
 */
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>
}