package com.yan.takeout.presenter

import com.yan.takeout.model.net.TakeoutService
import com.yan.takeout.ui.fragment.HomeFragment
import retrofit2.Retrofit



/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 11:47
 *  @description : home界面p层
 */
class HomeFragmentPresenter(val homeFragment: HomeFragment) {

    val service: TakeoutService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://192.168.56.1:8080/TakeoutService/")
                .build()

        service = retrofit.create<TakeoutService>(TakeoutService::class.java)
    }

    fun getHomeInfo() {
        //todo: 需要异步获取网络数据
        service.getHomeInfo()

        //有数据，成功页面
        homeFragment.onHomeSuccess()
        //无数据，异常页面
        homeFragment.onHomeFailed()
    }
}