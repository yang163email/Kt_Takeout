package com.yan.takeout.presenter

import com.yan.takeout.ui.fragment.HomeFragment

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 11:47
 *  @description : home界面p层
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment) {

    fun getHomeInfo() {
        //有数据，成功页面
        homeFragment.onHomeSuccess()
        //无数据，异常页面
        homeFragment.onHomeFailed()
    }
}