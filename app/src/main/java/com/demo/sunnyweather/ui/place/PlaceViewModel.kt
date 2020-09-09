package com.demo.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.sunnyweather.logic.Repository
import com.demo.sunnyweather.logic.model.Place
import retrofit2.http.Query

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
}