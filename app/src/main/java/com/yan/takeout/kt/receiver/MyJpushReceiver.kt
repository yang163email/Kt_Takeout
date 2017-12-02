package com.yan.takeout.kt.receiver

import android.content.Context
import android.util.Log
import cn.jpush.android.api.JPushMessage
import cn.jpush.android.service.JPushMessageReceiver

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 10:47
 *  @description : 自定义jpush广播
 */
class MyJpushReceiver : JPushMessageReceiver() {
    val TAG = javaClass.simpleName
    override fun onTagOperatorResult(p0: Context?, p1: JPushMessage?) {
        Log.d(TAG, "onTagOperatorResult: ")
    }

    override fun onAliasOperatorResult(p0: Context?, p1: JPushMessage?) {
        Log.d(TAG, "onAliasOperatorResult: ")
    }

    override fun onCheckTagOperatorResult(p0: Context?, p1: JPushMessage?) {
        Log.d(TAG, "onCheckTagOperatorResult: ")
    }
}