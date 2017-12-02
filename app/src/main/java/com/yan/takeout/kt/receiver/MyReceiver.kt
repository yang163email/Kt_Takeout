package com.yan.takeout.kt.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.heima.takeout.utils.OrderObservable


/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 11:12
 *  @description : 自定义接收jpush广播
 */
class MyReceiver: BroadcastReceiver() {
    val TAG = javaClass.simpleName
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras
        bundle?.let {
            val message = it.getString(JPushInterface.EXTRA_MESSAGE)
            Log.d(TAG, "onReceive: $message")
            val extra = it.getString(JPushInterface.EXTRA_EXTRA)
            extra?.let {
                //将消息发送给被观察者对象处理
                OrderObservable.instance.newMsgComing(extra)
            }
        }
    }
}