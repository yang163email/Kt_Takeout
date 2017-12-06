package com.yan.takeout.kt.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v13.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.LatLng
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.yan.takeout.kt.R
import org.jetbrains.anko.toast




/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 19:43
 *  @description : 地图定位界面
 */
class MapLocationActivity : AppCompatActivity(), PoiSearch.OnPoiSearchListener {
    override fun onPoiItemSearched(poiItem: PoiItem?, rCode: Int) {

    }

    override fun onPoiSearched(poiResult: PoiResult?, rCode: Int) {
        if (rCode == 1000) {
            //成功
            poiResult?.let {
                val poiItems = it.pois
                toast("一共有${poiItems.size}个兴趣点")
            }
        }
    }

    //声明AMapLocationClient类对象
    lateinit var mLocationClient: AMapLocationClient

    lateinit var aMap: AMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)

        val mapView = findViewById<View>(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)// 此方法必须重写
        aMap = mapView.map

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
                //移动到当前位置
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(it.latitude, it.longitude)))
                aMap.moveCamera(CameraUpdateFactory.zoomTo(17f))
                mLocationClient.stopLocation()
                doSearchQuery(it)
            }
        }
        //启动定位
        mLocationClient.startLocation()
    }

    private fun doSearchQuery(aMapLocation: AMapLocation) {
        val query = PoiSearch.Query("", "", aMapLocation.city)
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.pageSize = 30// 设置每页最多返回多少条poiitem
        query.pageNum = 1//设置查询页码

        val poiSearch = PoiSearch(this, query)
        poiSearch.bound = PoiSearch.SearchBound(LatLonPoint(aMapLocation.latitude, aMapLocation.longitude), 200)
        poiSearch.setOnPoiSearchListener(this)
        poiSearch.searchPOIAsyn()
    }
}