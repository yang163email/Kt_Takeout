package com.yan.takeout.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.model.beans.Seller
import com.yan.takeout.ui.fragment.HomeFragment
import org.json.JSONObject

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 11:47
 *  @description : home界面p层
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment): NetPresenter() {

    fun getHomeInfo() {
        //异步获取网络数据
        val homeCall = takeoutService.getHomeInfo()
        homeCall.enqueue(callback)
    }

    /**
     * 解析数据
     */
    override fun parseJson(json: String?) {
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