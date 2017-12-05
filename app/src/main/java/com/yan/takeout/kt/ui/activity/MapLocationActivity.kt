package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.amap.api.maps2d.MapView
import com.yan.takeout.kt.R



/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 19:43
 *  @description : 地图定位界面
 */
class MapLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)

        val mapView = findViewById<View>(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)// 此方法必须重写
        val aMap = mapView.map
    }
}