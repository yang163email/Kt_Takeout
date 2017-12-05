package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.kt.R
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_add_edit_receipt_address.*
import org.jetbrains.anko.dip

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 15:12
 *  @description : 添加收货地址页面
 */
class AddReceiptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_receipt_address)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_add_address.setPadding(0, 0 ,0, dip(48))
        }

        initListener()
    }

    private fun initListener() {
        ib_back.setOnClickListener { finish() }
    }

}