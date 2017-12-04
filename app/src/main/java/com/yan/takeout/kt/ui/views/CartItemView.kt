package com.yan.takeout.kt.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.heima.takeout.utils.PriceFormater
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.GoodsInfo
import kotlinx.android.synthetic.main.item_cart.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/4 11:54
 *  @description : 购物车列表的item
 */
class CartItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_cart, this)
    }

    fun bindView(goodsInfo: GoodsInfo) {
        tv_name.text = goodsInfo.name
        val price = PriceFormater.format(goodsInfo.count * goodsInfo.newPrice.toFloat())
        tv_type_all_price.text = price
        tv_count.text = goodsInfo.count.toString()

        ib_add.setOnClickListener { doAddOperation(ib_add, goodsInfo) }
        ib_minus.setOnClickListener { doMinusOperation(ib_minus, goodsInfo) }
    }

    private fun doMinusOperation(ibMinus: View, goodsInfo: GoodsInfo) {
        itemListener?.invoke(ibMinus, goodsInfo)
    }

    private fun doAddOperation(ibAdd: View, goodsInfo: GoodsInfo) {
        itemListener?.invoke(ibAdd, goodsInfo)
    }

    private var itemListener: ((view: View, goodsInfo: GoodsInfo) -> Unit)? = null

    fun  setOnItemClickListener(listener: (view: View, goodsInfo: GoodsInfo) -> Unit) {
        itemListener = listener
    }
}