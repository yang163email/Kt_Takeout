package com.yan.takeout.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.model.beans.Order
import com.yan.takeout.ui.views.OrderItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 9:29
 *  @description : 订单RecyclerView的adapter
 */
class OrderRvAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val orderList = mutableListOf<Order>()

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