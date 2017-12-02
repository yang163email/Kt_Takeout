package com.yan.takeout.kt.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import org.json.JSONObject

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 17:13
 *  @description : 商品p层
 */
class GoodsFragmentPresenter(val goodsFragment: GoodsFragment): NetPresenter() {

    fun getBusinessInfo(sellerId: String) {
        val businessCall = takeoutService.getBusinessInfo(sellerId)
        businessCall.enqueue(callback)
    }

    override fun parseJson(json: String?) {
        val jsonObj = JSONObject(json)
        val allStr = jsonObj.getString("list")
        val goodsTypeList: List<GoodsTypeInfo> = Gson().fromJson(allStr, object : TypeToken<List<GoodsTypeInfo>>(){}.type)
        Log.d(TAG, "parseJson: ${goodsTypeList.size}")
    }
}