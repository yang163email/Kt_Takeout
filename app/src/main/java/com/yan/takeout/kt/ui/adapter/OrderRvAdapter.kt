package com.yan.takeout.kt.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.heima.takeout.utils.OrderObservable
import com.yan.takeout.kt.model.beans.Order
import com.yan.takeout.kt.ui.views.OrderItemView
import java.util.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 9:29
 *  @description : 订单RecyclerView的adapter,同时是观察者对象
 */
class OrderRvAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Observer {
    val TAG = javaClass.simpleName

    private val orderList = mutableListOf<Order>()

    init {
        //将观察者与被观察者进行绑定
        OrderObservable.instance.addObserver(this)
    }

    override fun update(observable: Observable?, arg: Any?) {
        //接收到更新的消息
        Log.d(TAG, "update: $arg")
    }

    fun setOrderList(orders: List<Order>) {
        orderList.clear()
        orderList.addAll(orders)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val orderItemView = holder?.itemView as OrderItemView
        orderItemView.bindView(orderList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            OrderItemViewHolder(OrderItemView(context))

    class OrderItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}