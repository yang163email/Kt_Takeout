package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
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
}