package com.yan.takeout.kt.presenter

import android.util.Log
import com.yan.takeout.kt.model.beans.ResponseInfo
import com.yan.takeout.kt.model.net.TakeoutService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:41
 *  @description : 所有presenter的基类
 */
abstract class NetPresenter {

    protected val TAG = javaClass.simpleName

    val takeoutService: TakeoutService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/TakeoutService/")
                .addConverterFactory(GsonConverterFactory.create()) //添加gson转换工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加rxjava适配工厂
                .build()

        takeoutService = retrofit.create<TakeoutService>(TakeoutService::class.java)
    }

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