package com.yan.takeout.model.beans

data class Order(
        var id: String?,
        var type: String?,
        var seller: Seller?,
        var rider: Rider?,
        var goodsInfos: List<GoodsInfo>?,
        var distribution: Distribution?,
        var detail: OrderDetail?)

data class Rider(
        var id: Int,
        var name: String?,
        var phone: String?)
data class OrderDetail (
        var username: String?,
        var phone: String?,
        var address: String?,
        var pay: String?,
        var time: String?)

data class Distribution(
    // 配送方式
    var type: String?,
    // 配送说明
    var des: String?)
