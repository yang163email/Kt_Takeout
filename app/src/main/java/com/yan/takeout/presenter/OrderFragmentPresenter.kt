package com.yan.takeout.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.model.beans.Order
import com.yan.takeout.ui.fragment.OrderFragment

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 20:26
 *  @description : 订单界面p层
 */
class OrderFragmentPresenter(val orderFragment: OrderFragment): NetPresenter() {
    override fun parseJson(json: String?) {
        val orderList: List<Order> = Gson().fromJson(json, object : TypeToken<List<Order>>(){}.type)
        if (orderList.isNotEmpty()) {
            //size>0
            orderFragment.onOrderSuccess(orderList)
        } else {
            orderFragment.onOrderFailed()
        }
    }

    fun getOrderList(userId: String) {
        val orderCall = takeoutService.getOrderList(userId)
        orderCall.enqueue(callback)
    }
}