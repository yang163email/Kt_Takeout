package com.yan.takeout.kt.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.GoodsInfo
import kotlinx.android.synthetic.main.item_goods.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/3 9:59
 *  @description : 商品条目view
 */
class GoodsItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_goods, this)
    }

    fun bindView(goodsInfo: GoodsInfo) {
        tv_name.text = goodsInfo.name
    }
}