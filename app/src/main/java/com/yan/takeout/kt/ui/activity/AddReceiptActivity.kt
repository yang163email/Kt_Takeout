package com.yan.takeout.kt.ui.activity

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.yan.takeout.kt.R
import com.yan.takeout.kt.utils.CheckUtil
import com.yan.takeout.kt.utils.DeviceUtil
import com.yan.takeout.kt.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_add_edit_receipt_address.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.toast

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
        ib_add_phone_other.setOnClickListener { rl_phone_other.visibility = View.VISIBLE }
        ib_delete_phone.setOnClickListener { et_phone.setText("") }
        ib_delete_phone_other.setOnClickListener { et_phone_other.setText("") }
        ib_select_label.setOnClickListener { selectLabel() }
        btn_ok.setOnClickListener {
            val isOk = checkReceiptAddressInfo()
            if (isOk) {
                toast("可以新增地址了")
            }
        }
        et_phone.addTextChangedListener(object : TextWatcherAdapter() {

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    ib_delete_phone.visibility = View.VISIBLE
                } else {
                    ib_delete_phone.visibility = View.INVISIBLE
                }
            }
        })
        et_phone_other.addTextChangedListener(object : TextWatcherAdapter() {

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    ib_delete_phone_other.visibility = View.VISIBLE
                } else {
                    ib_delete_phone_other.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun checkReceiptAddressInfo(): Boolean {
        val name = et_name.getText().toString().trim()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写联系人", Toast.LENGTH_SHORT).show()
            return false
        }
        val phone = et_phone.getText().toString().trim()
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!CheckUtil.isMobileNO(phone)) {
            Toast.makeText(this, "请填写合法的手机号", Toast.LENGTH_SHORT).show()
            return false
        }
        val receiptAddress = et_receipt_address.getText().toString().trim()
        if (TextUtils.isEmpty(receiptAddress)) {
            Toast.makeText(this, "请填写收获地址", Toast.LENGTH_SHORT).show()
            return false
        }
        val address = et_detail_address.getText().toString().trim()
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private val titles = arrayOf("无", "家", "学校", "公司")
    private val colors = arrayOf("#778899", "#ff3399", "#ff9933", "#33ff99")

    private var labelDialog: AlertDialog? = null
    private fun selectLabel() {
        if (labelDialog == null) {
            labelDialog = AlertDialog.Builder(this)
                    .setTitle("请选择地址标签")
                    .setItems(titles) { dialog, which ->
                        tv_label.text = titles[which]
                        tv_label.setBackgroundColor(Color.parseColor(colors[which]))
                        tv_label.setTextColor(Color.BLACK)
                    }.create()
        }
        labelDialog?.show()
    }

}