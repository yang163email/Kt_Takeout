package com.yan.takeout.kt.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yan.takeout.kt.model.beans.GoodsInfo
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import com.yan.takeout.kt.model.dao.CacheSelectedInfoDao
import com.yan.takeout.kt.ui.activity.BusinessActivity
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import org.jetbrains.anko.collections.forEachWithIndex
import org.json.JSONObject

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 17:13
 *  @description : 商品p层
 */
class GoodsFragmentPresenter(val goodsFragment: GoodsFragment) : NetPresenter() {

    fun getBusinessInfo(sellerId: String) {
        val businessCall = takeoutService.getBusinessInfo(sellerId)
        businessCall.enqueue(callback)
    }

    override fun parseJson(json: String?) {
        val jsonObj = JSONObject(json)
        val allStr = jsonObj.getString("list")
        val goodsTypeList: List<GoodsTypeInfo> = Gson().fromJson(allStr, object : TypeToken<List<GoodsTypeInfo>>() {}.type)
        //是否有点餐记录
        val hasSelectInfo = (goodsFragment.activity as BusinessActivity).hasSelectInfo

        goodsTypeList.forEach { outer ->
            var aTypeCount = 0
            if (hasSelectInfo) {
                //如果有点餐数据
                aTypeCount = CacheSelectedInfoDao.queryCacheSelectedInfoByTypeId(outer.id)
                outer.redDotCount = aTypeCount
            }
            val aTypeList = outer.list
            aTypeList.forEach { inner ->
                if (aTypeCount > 0) {
                    //如果有具体类别商品
                    val count = CacheSelectedInfoDao.queryCacheSelectedInfoByGoodsId(inner.id)
                    inner.count = count
                }
            }
        }
        goodsFragment.onLoadBusinessSuccess(goodsTypeList)
        //更新购物车UI
        (goodsFragment.activity as BusinessActivity).updateCartUI()
    }

    /**
     * 根据商品类别id找到对应商品位置
     */
    fun getGoodsPositionByTypeId(goodsList: MutableList<GoodsInfo>, typeId: Int): Int {
        goodsList.forEachWithIndex { i, goodsInfo ->
            if (goodsInfo.typeId == typeId) {
                //找到了
                return i
            }
        }
        //默认-1表示为找到
        return -1
    }

    /**
     * 根据typeId找到对应左侧的position
     */
    fun getTypePositionByTypeId(goodsTypeList: List<GoodsTypeInfo>, newTypeId: Int): Int {
        goodsTypeList.forEachWithIndex { i, goodsTypeInfo ->
            if (goodsTypeInfo.id == newTypeId) {
                return i
            }
        }
        //默认-1表示为找到
        return -1
    }

    /**
     * 获取购物车的商品
     */
    fun getCartList(): List<GoodsInfo> {
        val cartList = mutableListOf<GoodsInfo>()
        goodsFragment.goodsList.forEach {
            if (it.count > 0) {
                cartList.add(it)
            }
        }
        return cartList
    }

    /**
     * 清空购物车
     */
    fun clearCart() {
        val cartList = getCartList()
        cartList.forEach {
            it.count = 0
        }
    }
}