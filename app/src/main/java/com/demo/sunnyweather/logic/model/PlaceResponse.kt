package com.demo.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @package:    com.demo.sunnyweather.logic.model
 * @author：    1668626317@qq.com
 * @describe：  城市数据模型
 * @time：      2020/9/9 11:29
 */
data class PlaceResponse(val status: String, val places: List<Place>)
data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)