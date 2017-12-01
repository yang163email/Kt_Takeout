package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import kotlinx.android.synthetic.main.fragment_order.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 订单
 */
class OrderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_order, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rv_order_list.layoutManager = LinearLayoutManager(activity)
    }
}