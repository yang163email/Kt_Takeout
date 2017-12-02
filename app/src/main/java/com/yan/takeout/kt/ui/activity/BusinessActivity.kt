package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.kt.R
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_business.*
import org.jetbrains.anko.dip

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:00
 *  @description : 商品列表界面
 */
class BusinessActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            fl_Container.setPadding(0, 0 ,0, dip(48))
        }
    }
}