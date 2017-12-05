package com.yan.takeout.kt.model.db

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(username)
        writeString(gender)
        writeString(phone)
        writeString(phoneOther)
        writeString(address)
        writeString(detailAddress)
        writeString(label)
        writeString(userId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ReceiptAddressBean> = object : Parcelable.Creator<ReceiptAddressBean> {
            override fun createFromParcel(source: Parcel): ReceiptAddressBean = ReceiptAddressBean(source)
            override fun newArray(size: Int): Array<ReceiptAddressBean?> = arrayOfNulls(size)
        }
    }
}