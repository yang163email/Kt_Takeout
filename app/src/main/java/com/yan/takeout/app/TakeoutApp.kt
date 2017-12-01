package com.yan.takeout.app

import com.mob.MobApplication
import com.yan.takeout.model.beans.User

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 15:09
 *  @description : 外卖项目Application
 */
class TakeoutApp: MobApplication() {
    companion object {
        var sUser = User()
    }

    override fun onCreate() {
        super.onCreate()
        sUser.id = -1   //id=-1表示未登录
    }
}