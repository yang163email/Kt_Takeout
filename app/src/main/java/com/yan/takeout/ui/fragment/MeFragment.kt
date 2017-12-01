package com.yan.takeout.ui.fragment

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import com.yan.takeout.app.TakeoutApp
import com.yan.takeout.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 个人
 */
class MeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        login.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        //展示登录成功后的UI效果
        val user = TakeoutApp.sUser
        if (user.id == -1) {
            //未登录
            ll_userinfo.visibility = View.GONE
            login.visibility = View.VISIBLE
        } else {
            login.visibility = View.GONE
            ll_userinfo.visibility = View.VISIBLE
            username.text = "欢迎您${user.name}"
            phone.text = user.phone
        }
    }
}