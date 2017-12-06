package com.yan.takeout.kt.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.db.ReceiptAddressBean
import com.yan.takeout.kt.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_confirm_order.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.startActivityForResult

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 14:47
 *  @description : 确认订单页面
 */
class ConfirmOrderActivity: AppCompatActivity() {

    companion object {
        const val REQ_CODE = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_confirm_order.setPadding(0, 0 ,0, dip(48))
        }

        rl_location.setOnClickListener { startActivityForResult<ReceiptActivity>(REQ_CODE) }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            data?.let {
                val addressBean = it.getParcelableExtra<ReceiptAddressBean>("address")
                tv_name.text = addressBean.username
                tv_sex.text = addressBean.gender
                tv_phone.text = "${addressBean.phone},${addressBean.phoneOther}"
                tv_address.text = "${addressBean.address},${addressBean.detailAddress}"
            }
        }
    }
}