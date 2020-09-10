package com.demo.sunnyweather.ui.place

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.demo.sunnyweather.R
import com.demo.sunnyweather.logic.model.Place

/**
 * @package:    com.demo.sunnyweather.ui.place
 * @author：    1668626317@qq.com
 * @describe：
 * @time：      2020/9/9 16:19
 */
class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    val TAG = "PlaceFragment"
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.palce_item, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList.get(position)
        holder.placeAddress.text =place.address
        holder.placeName.text = place.name
        holder.itemView.setOnClickListener {
            Toast.makeText(fragment.activity,"xxx",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return placeList.size
    }


}