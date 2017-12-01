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
            userDao.create(user)
            Log.d(TAG, "parseJson: 缓存用户数据到数据库中")
            loginActivity.onLoginSuccess()
        }else {
            loginActivity.onLoginFailed()
        }
    }
}