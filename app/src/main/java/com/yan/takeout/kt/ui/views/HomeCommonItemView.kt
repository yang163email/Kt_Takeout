package com.yan.takeout.kt.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.takeout.kt.R
import kotlinx.android.synthetic.main.item_home_common.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 21:12
 *  @description : TODO
 */
class HomeCommonItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.item_home_common, this)
    }

    fun bindView(s: String) {
        tv.text = s
    }
}