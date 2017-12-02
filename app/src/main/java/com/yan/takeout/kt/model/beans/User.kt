package com.yan.takeout.kt.model.beans

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 15:22
 *  @description ：登录用户bean
 */
@DatabaseTable(tableName = "t_user")
class User {
        @DatabaseField(id = true) var id: Int = 0       //id=true表示使用指定id
        @DatabaseField(columnName = "name") var name: String? = null
        @DatabaseField(columnName = "balance") var balance: Float = 0f
        @DatabaseField(columnName = "discount") var discount: Int = 0
        @DatabaseField(columnName = "integral") var integral: Int = 0
        @DatabaseField(columnName = "phone") var phone: String? = null
}