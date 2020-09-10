package com.demo.sunnyweather.logic.network

import com.demo.sunnyweather.SunnyWeatherApplication
import com.demo.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @package:    com.demo.sunnyweather.logic.network
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 11:36
 */
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}