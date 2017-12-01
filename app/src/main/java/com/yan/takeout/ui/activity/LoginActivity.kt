package com.yan.takeout.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.yan.takeout.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 10:24
 *  @description : 登录Activity
 */
class LoginActivity: AppCompatActivity() {
    companion object {
        val TAG = "LoginActivity"
    }
    // 创建EventHandler对象
    val eventHandler = object :EventHandler() {
        override fun afterEvent(event: Int, result: Int, data: Any) {
            if (data is Throwable) {
                val msg = data.message
                runOnUiThread {
                    msg?.let { toast(it) }
                }
            } else {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 这里是验证成功的回调，可以处理验证成功后您自己的逻辑，需要注意的是这里不是主线程
                    Log.d(TAG, "afterEvent: 获取验证码成功")
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.d(TAG, "afterEvent: 提交验证码成功")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler)
    }

    private fun initListener() {
        iv_user_back.setOnClickListener { finish() }
        tv_user_code.setOnClickListener {
            val phoneNum = et_user_phone.text.toString().trim()
            SMSSDK.getVerificationCode("86", phoneNum)
        }
        iv_login.setOnClickListener {
            val phoneNum = et_user_phone.text.toString().trim()
            val code = et_user_code.text.toString().trim()
            SMSSDK.submitVerificationCode("86", phoneNum, code)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(eventHandler)
    }
}