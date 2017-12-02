package com.yan.takeout.kt.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.kt.model.beans.Order
import com.yan.takeout.kt.ui.fragment.OrderFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 20:26
 *  @description : 订单界面p层
 */
class OrderFragmentPresenter(private val orderFragment: OrderFragment): NetPresenter() {
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
//        val orderCall = takeoutService.getOrderList(userId)
//        orderCall.enqueue(callback)
        val observable = takeoutService.getOrderListByRxJava(userId)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ parseJson(it.data) },
                        { Log.d(TAG, "onError: ${it.localizedMessage}") },
                        { Log.d(TAG, "onComplete: ") })
    }
}