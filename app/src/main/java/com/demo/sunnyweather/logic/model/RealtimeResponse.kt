package com.demo.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @package:    com.demo.sunnyweather.logic.model
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/10 9:55
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    /**
     * 空气质量
     */
    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}