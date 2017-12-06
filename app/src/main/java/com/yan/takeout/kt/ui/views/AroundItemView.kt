package com.yan.takeout.kt.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.amap.api.services.core.PoiItem
import com.yan.takeout.kt.R
import kotlinx.android.synthetic.main.item_around_address.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/6 12:42
 *  @description : 周边搜索item
 */
class AroundItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_around_address, this)
    }

    fun bindView(poiItem: PoiItem) {
        tv_title.text = poiItem.title
        tv_address.text = poiItem.snippet
    }

}