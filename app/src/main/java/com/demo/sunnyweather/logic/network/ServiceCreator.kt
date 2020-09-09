package com.demo.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @package:    com.demo.sunnyweather.logic.network
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 13:40
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T> create(serviceClass: Class<T>) = retrofit.create(serviceClass)
    inline fun <reified T> create() = create(T::class.java)
}