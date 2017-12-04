package com.yan.takeout.kt.model

import android.view.View
import com.yan.takeout.kt.model.beans.GoodsInfo

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/4 13:56
 *  @description : 购物车点击事件传递类
 */
data class EventCart(var goodsInfo: GoodsInfo, var view: View)