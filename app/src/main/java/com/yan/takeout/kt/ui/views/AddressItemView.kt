package com.yan.takeout.kt.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.db.ReceiptAddressBean
import kotlinx.android.synthetic.main.item_receipt_address.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 18:29
 *  @description : 地址条目view
 */
class AddressItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_receipt_address, this)
    }

    @SuppressLint("SetTextI18n")
    fun bindView(bean: ReceiptAddressBean) {
        tv_name.text = bean.username
        tv_phone.text = "${bean.phone},${bean.phoneOther}"
        tv_label.text = bean.label
        tv_address.text = "${bean.address},${bean.detailAddress}"
    }
}