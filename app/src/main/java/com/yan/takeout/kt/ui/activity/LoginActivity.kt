package com.yan.takeout.kt.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.heima.takeout.utils.SMSUtil
import com.yan.takeout.kt.R
import com.yan.takeout.kt.di.component.DaggerLoginActivityComponent
import com.yan.takeout.kt.di.module.LoginActivityModule
import com.yan.takeout.kt.presenter.LoginActivityPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 10:24
 *  @description : 登录Activity
 */
class LoginActivity: AppCompatActivity() {
    companion object {
        val TAG = "LoginActivity"
        val TIME_MINUS = -1
        val TIME_IS_OUT = -2
        val MIN = 1000L
    }

    var time = 60
    @Inject
    lateinit var loginActivityPresenter: LoginActivityPresenter

    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                TIME_MINUS -> {
                    tv_user_code.text = "剩余时间($time)秒"
                    time--
                    //如果time==0，发送另外一条消息，否则继续发送消息
                    if (time > 0) sendEmptyMessageDelayed(TIME_MINUS, MIN)
                    else sendEmptyMessageDelayed(TIME_IS_OUT, MIN)
                }
                TIME_IS_OUT -> {
                    tv_user_code.isEnabled = true
                    tv_user_code.text = "点击重发"
                    time = 60
                }
            }
        }
    }

    // 创建EventHandler对象
    val eventHandler = object : EventHandler() {
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
                    val phoneNum = et_user_phone.text.toString().trim()
                    loginActivityPresenter.loginByPhone(phoneNum)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()

        DaggerLoginActivityComponent.builder().
                loginActivityModule(LoginActivityModule(this))
                .build()
                .inject(this)
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler)
    }

    private fun initListener() {
        iv_user_back.setOnClickListener { finish() }
        tv_user_code.setOnClickListener {
            val phoneNum = et_user_phone.text.toString().trim()
            if (SMSUtil.judgePhoneNums(this, phoneNum)) {
                SMSSDK.getVerificationCode("86", phoneNum)
                tv_user_code.isEnabled = false
                handler.sendEmptyMessage(TIME_MINUS)
            } else {
                toast("请输入正确的手机号码")
            }
        }
        iv_login.setOnClickListener {
            val phoneNum = et_user_phone.text.toString().trim()
            val code = et_user_code.text.toString().trim()
            if (SMSUtil.judgePhoneNums(this, phoneNum) and !code.isEmpty()) {
                SMSSDK.submitVerificationCode("86", phoneNum, code)
            }
//            loginActivityPresenter.loginByPhone(phoneNum)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(eventHandler)
        handler.removeCallbacksAndMessages(null)
    }

    fun onLoginSuccess() {
        finish()
        toast("登录成功")
    }

    fun onLoginFailed() {
        toast("登录失败")
    }
}