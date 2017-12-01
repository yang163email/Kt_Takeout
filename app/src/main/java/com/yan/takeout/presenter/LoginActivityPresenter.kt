package com.yan.takeout.presenter

import com.google.gson.Gson
import com.yan.takeout.app.TakeoutApp
import com.yan.takeout.model.beans.User
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
            loginActivity.onLoginSuccess()
        }else {
            loginActivity.onLoginFailed()
        }
    }
}