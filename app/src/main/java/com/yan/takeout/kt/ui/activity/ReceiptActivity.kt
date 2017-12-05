package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.kt.R
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_address_list.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 15:12
 *  @description : 收货地址页面
 */
class ReceiptActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_address_list.setPadding(0, 0 ,0, dip(48))
        }

        initListener()
    }

    private fun initListener() {
        tv_add_address.setOnClickListener { startActivity<AddReceiptActivity>() }
    }
}