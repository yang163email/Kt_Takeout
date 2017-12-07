package com.yan.takeout.kt.app

import com.mob.MobApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.yan.takeout.kt.BuildConfig
import com.yan.takeout.kt.model.beans.CacheSelectedInfo
import com.yan.takeout.kt.model.dao.CacheSelectedInfoDao
import com.yan.takeout.kt.model.db.User
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

        if (BuildConfig.LOG_DEBUG) {
//            Timber.plant(Timber.DebugTree())
            Logger.addLogAdapter(AndroidLogAdapter())
        }
    }
}