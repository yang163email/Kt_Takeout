package com.yan.takeout.kt.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        handleIntent()
        //将观察者与被观察者进行绑定
        OrderObservable.instance.addObserver(this)
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
        }
    }
}