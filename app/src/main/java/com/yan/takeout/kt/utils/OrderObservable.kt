package com.heima.takeout.utils

import java.util.*

/**
 * 订单状态被观察者
 */
class OrderObservable private constructor() : Observable() {
    companion object {
        val instance = OrderObservable()

        /* 订单状态
       * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单*/
        val ORDERTYPE_UNPAYMENT = "10"
        val ORDERTYPE_SUBMIT = "20"
        val ORDERTYPE_RECEIVEORDER = "30"
        val ORDERTYPE_DISTRIBUTION = "40"
        // 骑手状态：接单、取餐、送餐
        val ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE = "43"
        val ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL = "46"
        val ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL = "48"

        val ORDERTYPE_SERVED = "50"
        val ORDERTYPE_CANCELLEDORDER = "60"
    }

    fun newMsgComing(extras: String) {
        //从广播接受者获取到最新消息
        //通知所有观察者，新消息来了
        setChanged()
        notifyObservers(extras)
    }

    fun getOrderTypeInfo(type: String): String {
        /**
         * 订单状态
         * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单
         */
        //            public static final String ORDERTYPE_UNPAYMENT = "10";
        //            public static final String ORDERTYPE_SUBMIT = "20";
        //            public static final String ORDERTYPE_RECEIVEORDER = "30";
        //            public static final String ORDERTYPE_DISTRIBUTION = "40";
        //            public static final String ORDERTYPE_SERVED = "50";
        //            public static final String ORDERTYPE_CANCELLEDORDER = "60";

        var typeInfo = ""
        when (type) {
            ORDERTYPE_UNPAYMENT -> typeInfo = "未支付"
            ORDERTYPE_SUBMIT -> typeInfo = "已提交订单"
            ORDERTYPE_RECEIVEORDER -> typeInfo = "商家接单"
            ORDERTYPE_DISTRIBUTION -> typeInfo = "配送中"
            ORDERTYPE_SERVED -> typeInfo = "已送达"
            ORDERTYPE_CANCELLEDORDER -> typeInfo = "取消的订单"
        }
        return typeInfo
    }

    fun getIndex(type: String): Int {
        var index = -1
        //        String typeInfo = "";
        when (type) {
            ORDERTYPE_UNPAYMENT -> {
            }
            ORDERTYPE_SUBMIT ->
                //                typeInfo = "已提交订单";
                index = 0
            ORDERTYPE_RECEIVEORDER ->
                //                typeInfo = "商家接单";
                index = 1
            ORDERTYPE_DISTRIBUTION,
            ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL,
            ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL,
            ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE ->
                //                typeInfo = "配送中";
                index = 2
            ORDERTYPE_SERVED ->
                //                typeInfo = "已送达";
                index = 3
            ORDERTYPE_CANCELLEDORDER -> {
            }
        }//                typeInfo = "未支付";
        //                typeInfo = "取消的订单";
        return index
    }
}
