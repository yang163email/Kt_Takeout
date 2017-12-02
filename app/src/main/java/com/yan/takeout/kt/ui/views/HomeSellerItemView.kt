package com.yan.takeout.kt.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.Seller
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

    @SuppressLint("SetTextI18n")
    fun bindView(seller: Seller) {
        tv_title.text = seller.name
        Picasso.with(context).load(seller.icon).into(seller_logo)
        ratingBar.rating = seller.score?.toFloat() ?: 0f
        tv_home_sale.text = seller.sale
        tv_home_send_price.text = "￥${seller.deliveryFee}起送/配送费￥${seller.sendPrice}"
        tv_home_distance.text = seller.distance

    }
}