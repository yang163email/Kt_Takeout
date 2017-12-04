package com.yan.takeout.kt.model.beans

import java.io.Serializable

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 12:41
 *  @description : 商家bean
 */

data class Seller(
		var activityList: List<ActivityInfo?>?,
		var deliveryFee: String = "", //4
		var distance: String?, //200米/33分钟
		var ensure: String?,
		var icon: String?, //http://10.0.2.2:8080/TakeoutService/imgs/seller/0.jpg
		var id: Int, //0
		var invoice: String?,
		var name: String?, //附近第0家分店
		var pic: String?,
		var recentVisit: String?,
		var sale: String?, //月售99份
		var score: String?, //5
		var sendPrice: String = "", //20
		var time: String?
) : Serializable