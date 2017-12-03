package com.yan.takeout.kt.ui.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import kotlinx.android.synthetic.main.item_type.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/3 8:42
 *  @description : 商品类别item
 */
class GoodsTypeItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_type, this)
    }

    fun bindView(goodsTypeInfo: GoodsTypeInfo, isSelected: Boolean) {
        if (isSelected) {
            //如果选中当前条目
            setBackgroundColor(Color.WHITE)
            type.setTextColor(Color.BLACK)
            type.typeface = Typeface.DEFAULT_BOLD
        } else {
            setBackgroundColor(Color.parseColor("#b9dedcdc"))
            type.setTextColor(Color.GRAY)
            type.typeface = Typeface.DEFAULT
        }
        type.text = goodsTypeInfo.name
    }
}