package com.yan.takeout.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.takeout.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 10:24
 *  @description : 登录Activity
 */
class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()
    }

    private fun initListener() {
        iv_user_back.setOnClickListener { finish() }
    }
}