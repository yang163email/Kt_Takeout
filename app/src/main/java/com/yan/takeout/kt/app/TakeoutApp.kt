package com.yan.takeout.kt.app

import com.mob.MobApplication
import com.yan.takeout.kt.model.beans.CacheSelectedInfo
import com.yan.takeout.kt.model.beans.User
import com.yan.takeout.kt.model.dao.CacheSelectedInfoDao
import java.util.concurrent.CopyOnWriteArrayList

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 15:09
 *  @description : 外卖项目Application
 */
class TakeoutApp: MobApplication() {
    companion object {
        var sUser = User()
    }

    val infos = CopyOnWriteArrayList<CacheSelectedInfo>()

    override fun onCreate() {
        super.onCreate()
        sUser.id = -1   //id=-1表示未登录
        CacheSelectedInfoDao.infos = infos
    }
}