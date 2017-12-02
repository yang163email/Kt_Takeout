package com.yan.takeout.kt.ui.fragment

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.di.component.DaggerHomeFragmentComponent
import com.yan.takeout.kt.di.module.HomeFragmentModule
import com.yan.takeout.kt.R
import com.yan.takeout.kt.model.beans.Seller
import com.yan.takeout.kt.presenter.HomeFragmentPresenter
import com.yan.takeout.kt.ui.adapter.HomeRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 首页
 */
class HomeFragment: Fragment() {

    private lateinit var homeRvAdapter: HomeRvAdapter
    private val sellerList = mutableListOf<Seller>()

    @Inject
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
        }
//        homeFragmentPresenter = HomeFragmentPresenter(this)
        //通过dagger2创建presenter
        DaggerHomeFragmentComponent.builder()
                .homeFragmentModule(HomeFragmentModule(this))
                .build()
                .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeFragmentPresenter.getHomeInfo()
    }

    fun onHomeSuccess(nearbySellers: List<Seller>, otherSellers: List<Seller>) {
        sellerList.clear()
        sellerList.addAll(nearbySellers)
        sellerList.addAll(otherSellers)
        homeRvAdapter.setDatas(sellerList)

        rv_home.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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

    fun onHomeFailed() {
        toast("获取数据失败")
    }
}