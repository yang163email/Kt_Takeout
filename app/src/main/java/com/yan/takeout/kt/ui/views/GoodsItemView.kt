package com.yan.takeout.kt.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.heima.takeout.utils.PriceFormater
import com.squareup.picasso.Picasso
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

    @SuppressLint("SetTextI18n")
    fun bindView(goodsInfo: GoodsInfo) {
        tv_name.text = goodsInfo.name
        Picasso.with(context).load(goodsInfo.icon).into(iv_icon)
        tv_form.text = goodsInfo.form
        tv_month_sale.text = "月售${goodsInfo.monthSaleNum}份"
        val newPrice = PriceFormater.format(goodsInfo.newPrice.toFloat())
        val oldPrice = PriceFormater.format(goodsInfo.oldPrice.toFloat())
        tv_newprice.text = newPrice
        tv_oldprice.text = oldPrice
        tv_oldprice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

        if (goodsInfo.oldPrice == 0) {
            //如果oldprice为0，则不显示
            tv_oldprice.visibility = View.GONE
        } else {
            tv_oldprice.visibility = View.VISIBLE
        }

        if (goodsInfo.count > 0) {
            tv_count.visibility = View.VISIBLE
            ib_minus.visibility = View.VISIBLE
        } else {
            tv_count.visibility = View.INVISIBLE
            ib_minus.visibility = View.INVISIBLE
        }
    }
}