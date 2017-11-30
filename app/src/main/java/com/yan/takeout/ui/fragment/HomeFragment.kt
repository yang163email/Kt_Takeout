package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import com.yan.takeout.presenter.HomeFragmentPresenter
import com.yan.takeout.ui.adapter.HomeRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.toast

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 首页
 */
class HomeFragment: Fragment() {

    private lateinit var homeRvAdapter: HomeRvAdapter
    private val mDataList = mutableListOf<String>()
    lateinit var homeFragmentPresenter: HomeFragmentPresenter
    //RecyclerView滑动的距离
    var sum = 0
    //颜色渐变的距离
    var distance = 0
    //alpha渐变范围
    var alphaRange = 0xff - 0x55
    var alpha = 0x55

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        distance = dip(120)
        rv_home.apply {
            layoutManager = LinearLayoutManager(activity)
            homeRvAdapter = HomeRvAdapter(activity)
            adapter = homeRvAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    sum += dy
                    if (sum > distance) {
                        this@HomeFragment.alpha = 0xff
                    } else {
                        this@HomeFragment.alpha = alphaRange * sum / distance
                    }
                    ll_title_container.setBackgroundColor(
                            Color.argb(this@HomeFragment.alpha, 0x31, 0x90, 0xe8))
                }
            })
        }
        homeFragmentPresenter = HomeFragmentPresenter(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initTempData()
        homeFragmentPresenter.getHomeInfo()
        homeRvAdapter.setDatas(mDataList)
    }

    private fun initTempData() {
        (0..100).forEach {
            mDataList.add("条目$it")
        }
    }

    fun onHomeSuccess() {
        toast("获取数据成功")
    }

    fun onHomeFailed() {
        toast("获取数据失败")
    }
}