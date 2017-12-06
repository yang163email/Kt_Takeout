package com.yan.takeout.kt.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.*
import com.heima.takeout.utils.OrderObservable
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.Order
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.json.JSONObject
import java.util.*




/**
 *  @author      : 楠GG
 *  @date        : 2017/12/6 14:48
 *  @description : 订单详情界面
 */
class OrderDetailActivity : AppCompatActivity(), Observer {

    private lateinit var order: Order
    private lateinit var aMap: AMap
    private lateinit var riderMarker: Marker
    private val points = mutableListOf<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        handleIntent()
        //将观察者与被观察者进行绑定
        OrderObservable.instance.addObserver(this)

        mMapView.onCreate(savedInstanceState)// 此方法必须重写
        aMap = mMapView.map
    }

    private fun handleIntent() {
        if (intent.hasExtra("order")) {
            order = intent.getSerializableExtra("order") as Order
            showState()
        }
    }

    private fun showState() {
        val type = order.type
        val index = OrderObservable.instance.getIndex(type)

        val imageView = ll_order_detail_type_point_container.getChildAt(index) as ImageView
        imageView.setImageResource(R.mipmap.order_time_node_disabled)
        val textView = ll_order_detail_type_container.getChildAt(index) as TextView
        textView.setTextColor(Color.BLUE)
    }

    override fun update(observable: Observable?, arg: Any?) {
        //接收到更新的消息
        val jsonObj = JSONObject(arg as String)
        val pushOrderId = jsonObj.getString("orderId")
        val pushType = jsonObj.getString("type")
        if (order.id == pushOrderId) {
            order.type = pushType
            showState()
            when (order.type) {
                OrderObservable.ORDERTYPE_RECEIVEORDER -> {
                    //显示地图
                    receiveOrderState()
                }
                OrderObservable.ORDERTYPE_DISTRIBUTION -> {
                    //骑士登场
                    distributionState()
                }
                OrderObservable.ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL,
                OrderObservable.ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL -> {
                    //移动骑士，更新骑手位置
                    //先获取经纬度
                    riderTakeMealState(jsonObj)
                }
            }
        }
    }

    private fun riderTakeMealState(jsonObj: JSONObject) {
        //移动骑士，更新骑手位置
        //先获取经纬度
        val lat = jsonObj.getString("lat")
        val lng = jsonObj.getString("lng")
        riderMarker.hideInfoWindow()
        val latLng = LatLng(lat.toDouble(), lng.toDouble())
        riderMarker.position = latLng
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        //测距
        val distance = AMapUtils.calculateLineDistance(latLng,
                LatLng(22.5692040000, 113.9533410000))
        riderMarker.snippet = "距离您还有${Math.abs(distance)}米"
        riderMarker.showInfoWindow()

        //添加到集合中
        points.add(latLng)
        val polyline = aMap.addPolyline(
                PolylineOptions().color(Color.RED)
                        .width(2f)
                        .add(points[points.size - 1], points[points.size - 2])
        )
    }

    private fun distributionState() {
        //骑士登场
        //将坐标添加到集合中
        points.add(LatLng(22.5716940000, 113.9537550000))
        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.order_rider_icon)
        riderMarker = aMap.addMarker(MarkerOptions()
                .position(LatLng(22.5716940000, 113.9537550000))
                .icon(BitmapDescriptorFactory.fromView(imageView))
                .title("骑士"))
        riderMarker.showInfoWindow()
    }

    private fun receiveOrderState() {
        //显示地图
        mMapView.visibility = View.VISIBLE
        //当前位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(22.5693400000, 113.9537130000), 16f))
        //标注卖家位置    鸡煲店
        val sellerMarker = aMap.addMarker(MarkerOptions()
                .position(LatLng(22.5722970000, 113.9509130000))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.order_seller_icon))
                .title("鸡煲店").snippet("一个渺小的卖家"))
        //标注买家位置    B座
        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.order_buyer_icon)
        val buyerMarker = aMap.addMarker(MarkerOptions()
                .position(LatLng(22.5692040000, 113.9533410000))
                .icon(BitmapDescriptorFactory.fromView(imageView))
                .title("B座").snippet("一个霸气的买家"))
    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState)
    }
}