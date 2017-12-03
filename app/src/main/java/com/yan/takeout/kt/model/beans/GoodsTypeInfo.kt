package com.yan.takeout.kt.model.beans

data class GoodsTypeInfo(
    var id: Int,//商品类型id
    var name: String,//商品类型名称
    var info: String,//特价信息
    var list: List<GoodsInfo>,//商品列表
    var redDotCount: Int = 0
    )
