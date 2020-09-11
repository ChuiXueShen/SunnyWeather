package com.demo.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.sunnyweather.logic.Repository
import com.demo.sunnyweather.logic.dao.PlaceDao
import com.demo.sunnyweather.logic.model.Place

/**
 * @package:    com.demo.sunnyweather.ui.place
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 14:22
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
    fun savePlace(place: Place){
        Repository.savePlace(place)
    }
    //获取保存的城市
    fun getSavedPlace(): Place = Repository.getSavedPlace()
    //判断城市是否被保存
    fun isPlaceSaved(): Boolean = Repository.isPlaceSaved()
}