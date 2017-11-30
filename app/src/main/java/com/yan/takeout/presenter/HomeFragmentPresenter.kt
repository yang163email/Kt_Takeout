package com.yan.takeout.presenter

import android.util.Log
import com.yan.takeout.model.beans.ResponseInfo
import com.yan.takeout.model.net.TakeoutService
import com.yan.takeout.ui.fragment.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 11:47
 *  @description : home界面p层
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment) {
    companion object {
        val TAG = "HomeFragmentPresenter"
    }

    val takeoutService: TakeoutService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://192.168.56.1:8080/TakeoutService/")
                .build()

        takeoutService = retrofit.create<TakeoutService>(TakeoutService::class.java)
    }

    fun getHomeInfo() {
        //todo: 需要异步获取网络数据
        val homeCall = takeoutService.getHomeInfo()
        homeCall.enqueue(object : Callback<ResponseInfo>{
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
        })

        //有数据，成功页面
        homeFragment.onHomeSuccess()
        //无数据，异常页面
        homeFragment.onHomeFailed()
    }

    /**
     * 解析数据
     */
    private fun parseJson(json: String?) {

    }
}