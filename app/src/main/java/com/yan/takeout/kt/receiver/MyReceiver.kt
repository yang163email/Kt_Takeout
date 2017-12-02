package com.yan.takeout.kt.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 11:12
 *  @description : 自定义接收jpush广播
 */
class MyReceiver: BroadcastReceiver() {
    val TAG = javaClass.simpleName
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: 收到消息啦")
    }
}