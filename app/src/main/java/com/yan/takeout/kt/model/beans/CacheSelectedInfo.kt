package com.yan.takeout.kt.model.beans

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/4 16:02
 *  @description : 点餐缓存类
 */
data class CacheSelectedInfo(
        var sellerId: Int,  //商家Id
        var typeId: Int,    //类型id
        var goodsId: Int,   //商品id
        var count: Int      //商品数量
)