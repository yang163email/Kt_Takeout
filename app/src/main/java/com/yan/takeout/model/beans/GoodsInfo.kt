package com.yan.takeout.model.beans

data class GoodsInfo(
    var id: Int,    //商品id
    var name: String,   //商品名称
    var icon: String,   //商品图片
    var form: String,   //组成
    var monthSaleNum: Int, //月销售量
    var isBargainPrice: Boolean, //特价
    var isNew: Boolean, //是否是新产品
    var newPrice: String, //新价
    var oldPrice: Int, //原价
    var sellerId: Int = 0)
