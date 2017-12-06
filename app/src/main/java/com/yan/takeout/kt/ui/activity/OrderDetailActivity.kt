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

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/6 14:48
 *  @description : 订单详情界面
 */
class OrderDetailActivity : AppCompatActivity() {

    var order: Order? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        handleIntent()
    }

    private fun handleIntent() {
        if (intent.hasExtra("order")) {
            order = intent.getSerializableExtra("order") as Order
            order?.let {
                val type = it.type
                val index = OrderObservable.instance.getIndex(type)

                val imageView = ll_order_detail_type_point_container.getChildAt(index) as ImageView
                imageView.setImageResource(R.mipmap.order_time_node_disabled)
                val textView = ll_order_detail_type_container.getChildAt(index) as TextView
                textView.setTextColor(Color.BLUE)
            }
        }
    }
}