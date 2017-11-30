package com.yan.takeout.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.R
import com.yan.takeout.model.beans.Seller
import kotlinx.android.synthetic.main.item_seller.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 10:34
 *  @description : 首页商家条目view
 */
class HomeSellerItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_seller, this)
    }

    fun bindView(seller: Seller) {
        tv_title.text = seller.name
    }
}