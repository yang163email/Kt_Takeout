package com.yan.takeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.j256.ormlite.dao.Dao
import com.yan.takeout.app.TakeoutApp
import com.yan.takeout.model.beans.User
import com.yan.takeout.model.dao.TakeoutOpenHelper
import com.yan.takeout.ui.activity.LoginActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:22
 *  @description : 登录界面p层
 */
class LoginActivityPresenter(val loginActivity: LoginActivity): NetPresenter() {

    /**
     * 登录
     */
    fun loginByPhone(phone: String) {
        val loginCall = takeoutService.loginByPhone(phone)
        loginCall.enqueue(callback)
    }

    override fun parseJson(json: String?) {
        val user = Gson().fromJson(json, User::class.java)
        if (user != null) {
            //缓存到内存中
            TakeoutApp.sUser = user
            //缓存到本地数据库中
            val takeoutOpenHelper = TakeoutOpenHelper(loginActivity)
            val userDao: Dao<User, Int> = takeoutOpenHelper.getDao(User::class.java)
            val users = userDao.queryForAll()

            //申明变量，判断是否为老用户
            var isOldUser = false
            users.forEach {
                if (it.id == user.id) {
                    isOldUser = true
                }
            }
            if (isOldUser) {
                //老用户，更新数据库
                userDao.update(user)
                Log.d(TAG, "parseJson: 老用户登录")
            } else {
                //新用户，新增数据
                userDao.create(user)
                Log.d(TAG, "parseJson: 新用户登录")
            }
            loginActivity.onLoginSuccess()
        }else {
            loginActivity.onLoginFailed()
        }
    }
}