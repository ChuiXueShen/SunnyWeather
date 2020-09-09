package com.demo.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @package:    com.demo.sunnyweather
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 11:06
 */
class SunnyWeatherApplication:Application() {

    companion object{
        @SuppressLint("StaticfFieldLeak")
        lateinit var context: Context
        const val TOKEN = "PWBobiGgNzyaHv2W"
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }
}