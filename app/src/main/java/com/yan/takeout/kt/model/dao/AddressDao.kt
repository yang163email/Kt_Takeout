package com.yan.takeout.kt.model.dao

import android.content.Context
import android.util.Log
import com.j256.ormlite.dao.Dao
import com.yan.takeout.kt.model.db.ReceiptAddressBean

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 17:13
 *  @description : 地址 dao 层
 */
class AddressDao(context: Context) {
    private val TAG = javaClass.simpleName

    private val addressDao: Dao<ReceiptAddressBean, Int>
    init {
        val helper = TakeoutOpenHelper(context)
        addressDao = helper.getDao(ReceiptAddressBean::class.java)
    }

    fun addReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.create(bean)
        } catch (e: Exception) {
            Log.d(TAG, "addReceiptAddressBean: ${e.localizedMessage}")
        }
    }

    fun deleteReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.delete(bean)
        } catch (e: Exception) {
            Log.d(TAG, "deleteReceiptAddressBean: ${e.localizedMessage}")
        }
    }

    fun updateReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.update(bean)
        } catch (e: Exception) {
            Log.d(TAG, "updateReceiptAddressBean: ${e.localizedMessage}")
        }
    }

    fun queryAllAddress() {
        try {
            addressDao.queryForAll()
        } catch (e: Exception) {
            Log.d(TAG, "queryAllAddress: ${e.localizedMessage}")
        }
    }
}