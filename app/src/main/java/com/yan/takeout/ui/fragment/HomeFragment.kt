package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import com.yan.takeout.ui.adapter.HomeRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 首页
 */
class HomeFragment: Fragment() {

    private lateinit var homeRvAdapter: HomeRvAdapter
    private val mDataList = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rv_home.layoutManager = LinearLayoutManager(activity)
        homeRvAdapter = HomeRvAdapter(activity)
        rv_home.adapter = homeRvAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initTempData()
        homeRvAdapter.setDatas(mDataList)
    }

    private fun initTempData() {
        (0..100).forEach {
            mDataList.add("条目$it")
        }
    }
}