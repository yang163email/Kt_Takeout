package com.yan.takeout.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.R
import com.yan.takeout.model.beans.Order
import kotlinx.android.synthetic.main.item_order_item.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 9:33
 *  @description : 订单item
 */
class OrderItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_order_item, this)
    }

    fun bindView(order: Order) {
        tv_order_item_seller_name.text = order.seller?.name
        tv_order_item_type.text = order.type
    }
}