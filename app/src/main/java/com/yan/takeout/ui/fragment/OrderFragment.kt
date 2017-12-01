package com.yan.takeout.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.R
import com.yan.takeout.app.TakeoutApp
import com.yan.takeout.di.component.DaggerOrderFragmentComponent
import com.yan.takeout.di.module.OrderFragmentModule
import com.yan.takeout.model.beans.Order
import com.yan.takeout.presenter.OrderFragmentPresenter
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 12:45
 *  @description : 订单界面
 */
class OrderFragment : Fragment() {
    @Inject
    lateinit var orderPresenter: OrderFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_order, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rv_order_list.layoutManager = LinearLayoutManager(activity)
        DaggerOrderFragmentComponent.builder()
                .orderFragmentModule(OrderFragmentModule(this))
                .build()
                .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = TakeoutApp.sUser.id
        if (-1 == userId) {
            //未登录
            toast("请先登录，再查看订单")
        } else {
            orderPresenter.getOrderList(userId.toString())
        }
    }

    fun onOrderSuccess(orderList: List<Order>) {
        //给adapter设置数据
        toast("获取数据成功")
    }

    fun onOrderFailed() {
        toast("服务器繁忙")
    }
}