package com.yan.takeout.kt.utils

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 16:50
 *  @description : 数据检测工具类
 */
object CheckUtil {
    /**
     * 验证手机格式
     */
    fun isMobileNO(phone: String): Boolean {
        val telRegex = "[1][358]\\d{9}"//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return phone.matches(telRegex.toRegex())
    }
}