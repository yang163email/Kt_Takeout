package com.yan.takeout.kt.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v13.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps2d.MapView
import com.yan.takeout.kt.R
import org.jetbrains.anko.toast


/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 19:43
 *  @description : 地图定位界面
 */
class MapLocationActivity : AppCompatActivity() {

    //声明AMapLocationClient类对象
    lateinit var mLocationClient: AMapLocationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)

        val mapView = findViewById<View>(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)// 此方法必须重写
        val aMap = mapView.map

        checkPermission()
    }

    private val WRITE_COARSE_LOCATION_REQUEST_CODE: Int = 10

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    WRITE_COARSE_LOCATION_REQUEST_CODE) //自定义的code
        } else {
            initLocation()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //允许
            initLocation()
        } else {
            finish()
            toast("需要有定位权限才能成功定位")
        }
    }

    private fun initLocation() {
        //初始化定位
        mLocationClient = AMapLocationClient(applicationContext)
        //设置定位回调监听
        mLocationClient.setLocationListener {
            it?.let {
                toast(it.address)
                mLocationClient.stopLocation()
            }
        }
        //启动定位
        mLocationClient.startLocation()
    }
}