package com.yan.takeout.kt.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.yan.takeout.kt.model.db.ReceiptAddressBean
import com.yan.takeout.kt.model.db.User

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 16:37
 *  @description : 外卖数据库helper类
 */
class TakeoutOpenHelper(context: Context) :
        OrmLiteSqliteOpenHelper(context, "takeout_kotlin.db", null, 2) {
    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        //创建user表
        TableUtils.createTable(connectionSource, User::class.java)
        //创建address表
        TableUtils.createTable(connectionSource, ReceiptAddressBean::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        //app更新的时候走这里
        //创建address表
        TableUtils.createTable(connectionSource, ReceiptAddressBean::class.java)
    }
}