package com.yan.takeout.kt.model.db

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 16:56
 *  @description : 收货地址数据bean
 */
@DatabaseTable(tableName = "t_address")
data class ReceiptAddressBean(
        @DatabaseField(generatedId = true) var id: Int = 0,
        @DatabaseField(columnName = "username") var username: String = "",
        @DatabaseField(columnName = "gender") var gender: String = "男",
        @DatabaseField(columnName = "phone") var phone: String = "",
        @DatabaseField(columnName = "phoneOther") var phoneOther: String = "",
        @DatabaseField(columnName = "address") var address: String = "",
        @DatabaseField(columnName = "detailAddress") var detailAddress: String = "",
        @DatabaseField(columnName = "label") var label: String = "",
        @DatabaseField(columnName = "userId") var userId: String = "123"
)