package com.yan.takeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.model.beans.ResponseInfo
import com.yan.takeout.model.beans.Seller
import com.yan.takeout.model.net.TakeoutService
import com.yan.takeout.ui.fragment.HomeFragment
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                .baseUrl("http://192.168.56.1:8080/TakeoutService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        takeoutService = retrofit.create<TakeoutService>(TakeoutService::class.java)
    }

    fun getHomeInfo() {
        //异步获取网络数据
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

    }

    /**
     * 解析数据
     */
    private fun parseJson(json: String?) {
        val gson = Gson()
        val jsonObject = JSONObject(json)
        val nearby = jsonObject.getString("nearbySellerList")
        val nearbySellers: List<Seller> = gson.fromJson(nearby, object : TypeToken<List<Seller>>() {}.type)
        val other = jsonObject.getString("otherSellerList")
        val otherSellers: List<Seller> = gson.fromJson(other, object : TypeToken<List<Seller>>(){}.type)

        //刷新UI
        if (nearbySellers.isNotEmpty() || otherSellers.isNotEmpty()) {
            //有数据，成功页面
            homeFragment.onHomeSuccess(nearbySellers, otherSellers)
        } else {
            //无数据，异常页面
            homeFragment.onHomeFailed()
        }
    }
}