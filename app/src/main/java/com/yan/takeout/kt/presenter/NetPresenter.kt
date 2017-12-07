package com.yan.takeout.kt.presenter

import android.util.Log
import com.yan.takeout.kt.model.beans.ResponseInfo
import com.yan.takeout.kt.model.net.HttpManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:41
 *  @description : 所有presenter的基类
 */
abstract class NetPresenter {

    protected val TAG = javaClass.simpleName

    val takeoutService = HttpManager.instance.takeoutService

    val callback = object : Callback<ResponseInfo> {
        override fun onResponse(call: Call<ResponseInfo>?, response: Response<ResponseInfo>?) {
            if (response == null) {
                Log.d(TAG, "onResponse: 服务器没有成功返回")
            } else if (response.isSuccessful) {
                val responseInfo = response.body()
                if (responseInfo?.code == "0") {
                    val json = responseInfo.data
                    parseJson(json)
                } else if (responseInfo?.code == "1") {
                    //根据具体接口文档表示含义，对应的操作
                }
            } else {
                Log.d(TAG, "onResponse: 服务器代码错误")
            }
        }

        override fun onFailure(call: Call<ResponseInfo>?, t: Throwable?) {
            Log.d(TAG, "onFailure: 没有连上服务器")
        }
    }

    abstract fun parseJson(json: String?)
}