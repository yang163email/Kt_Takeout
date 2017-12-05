package com.yan.takeout.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.dao.AddressDao
import com.yan.takeout.kt.ui.adapter.AddressRvAdapter
import com.yan.takeout.kt.ui.views.RecycleViewDivider
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
    lateinit var addressDao: AddressDao
    lateinit var addressAdapter: AddressRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_address_list.setPadding(0, 0 ,0, dip(48))
        }

        addressDao = AddressDao(this)
        addressAdapter = AddressRvAdapter(this)
        rv_receipt_address.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = addressAdapter
            //添加分隔线
            addItemDecoration(RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL))
        }
        initListener()
    }

    private fun initListener() {
        tv_add_address.setOnClickListener { startActivity<AddOrEditActivity>() }
    }

    override fun onStart() {
        super.onStart()
        val allAddress = addressDao.queryAllAddress()
        if (allAddress.isNotEmpty()) {
            addressAdapter.setAddressList(allAddress)
        }
    }
}