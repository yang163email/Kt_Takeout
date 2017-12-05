package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.kt.R
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_confirm_order.*
import org.jetbrains.anko.dip

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 14:47
 *  @description : 确认订单页面
 */
class ConfirmOrderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_confirm_order.setPadding(0, 0 ,0, dip(48))
        }
    }
}