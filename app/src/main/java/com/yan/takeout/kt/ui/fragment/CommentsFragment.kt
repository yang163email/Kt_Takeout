package com.yan.takeout.kt.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:26
 *  @description : 评论fragment
 */
class CommentsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val textView = TextView(activity)
        textView.text = "评论"
        textView.gravity = Gravity.CENTER
        return textView
    }
}