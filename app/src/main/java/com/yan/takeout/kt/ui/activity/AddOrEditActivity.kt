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
import com.yan.takeout.kt.model.dao.AddressDao
import com.yan.takeout.kt.model.db.ReceiptAddressBean
import com.yan.takeout.kt.utils.CheckUtil
import com.yan.takeout.kt.utils.DeviceUtil
import com.yan.takeout.kt.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_add_edit_receipt_address.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 15:12
 *  @description : 添加/修改收货地址页面
 */
class AddOrEditActivity : AppCompatActivity() {

    private val titles = arrayOf("无", "家", "学校", "公司")
    private val colors = arrayOf("#778899", "#ff3399", "#ff9933", "#33ff99")

    private var labelDialog: AlertDialog? = null
    lateinit var addressDao: AddressDao
    var addressBean: ReceiptAddressBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_receipt_address)
        addressDao = AddressDao(this)
        handleIntent()
        if (DeviceUtil.checkDeviceHasNavigationBar(this)) {
            //如果有导航栏，设置底部边距
            activity_add_address.setPadding(0, 0 ,0, dip(48))
        }
        initListener()
    }

    private fun handleIntent() {
        if (intent.hasExtra("address")) {
            addressBean = intent.getParcelableExtra("address")
            //显示数据到界面上
            addressBean?.let {
                et_name.setText(it.username)
                if ("先生" == it.gender) rb_man.isChecked = true
                else rb_women.isChecked = true
                et_phone.setText(it.phone)
                et_phone_other.setText(it.phoneOther)
                et_receipt_address.setText(it.address)
                et_detail_address.setText(it.detailAddress)
                tv_label.text = it.label
                ib_delete.visibility = View.VISIBLE
                ib_delete.setOnClickListener { inner ->
                    //删除地址
                    addressDao.deleteReceiptAddressBean(it)
                    toast("删除成功")
                    finish()
                }
            }
        }
    }

    private fun initListener() {
        ib_back.setOnClickListener { finish() }
        ib_add_phone_other.setOnClickListener { rl_phone_other.visibility = View.VISIBLE }
        ib_delete_phone.setOnClickListener { et_phone.setText("") }
        ib_delete_phone_other.setOnClickListener { et_phone_other.setText("") }
        ib_select_label.setOnClickListener { selectLabel() }
        btn_ok.setOnClickListener { clickConfirm() }
        btn_location_address.setOnClickListener { startActivity<MapLocationActivity>() }
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

    private fun clickConfirm() {
        val isOk = checkReceiptAddressInfo()
        if (isOk) {
            val username = et_name.text.toString().trim()
            var gender = "女士"
            if (rb_man.isChecked) gender = "先生"
            val phone = et_phone.text.toString().trim()
            val phoneOther = et_phone_other.text.toString().trim()
            val address = et_receipt_address.text.toString().trim()
            val detailAddress = et_detail_address.text.toString().trim()
            val label = tv_label.text.toString()
            if (addressBean == null)
                insertAddress(username, gender, phone, phoneOther, address, detailAddress, label)
            else
                updateAddress(username, gender, phone, phoneOther, address, detailAddress, label)
            finish()
        }
    }

    /**
     * 更新地址
     */
    private fun updateAddress(username: String, gender: String, phone: String, phoneOther: String,
                              address: String, detailAddress: String, label: String) {
        addressBean?.let {
            it.username = username
            it.gender = gender
            it.phone = phone
            it.phoneOther = phoneOther
            it.address = address
            it.detailAddress = detailAddress
            it.label = label
            addressDao.updateReceiptAddressBean(it)
        }
        toast("修改成功")
    }

    /**
     * 新增地址
     */
    private fun insertAddress(username: String, gender: String, phone: String, phoneOther: String,
                              address: String, detailAddress: String, label: String) {
        val addressBean = ReceiptAddressBean(999, username, gender, phone, phoneOther, address, detailAddress, label)
        addressDao.addReceiptAddressBean(addressBean)
        toast("新增成功")
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