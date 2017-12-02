package com.yan.takeout.kt.ext

import android.content.Context
import android.util.TypedValue

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 19:29
 *  @description : 扩展工具类
 */

/**
 * 把转化功能添加到Int类中作为扩展函数
 */
fun Int.dp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), context.resources.displayMetrics).toInt()
}