package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yan.takeout.R

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 更多
 */
class MoreFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_, null)
        (view as TextView).text = "更多"
        return view
    }
}