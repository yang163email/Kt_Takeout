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
import com.yan.takeout.ui.adapter.OrderRvAdapter
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
    lateinit var orderRvAdapter: OrderRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_order, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        DaggerOrderFragmentComponent.builder()
                .orderFragmentModule(OrderFragmentModule(this))
                .build()
                .inject(this)
        orderRvAdapter = OrderRvAdapter(activity)

        rv_order_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = orderRvAdapter
        }

        srl_order.setOnRefreshListener {
            loadOrderList()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadOrderList()
    }

    /**
     * 加载订单数据
     */
    private fun loadOrderList() {
        val userId = TakeoutApp.sUser.id
        if (-1 == userId) {
            //未登录
            toast("请先登录，再查看订单")
            srl_order.isEnabled = false
        } else {
            srl_order.isEnabled = true
            orderPresenter.getOrderList(userId.toString())
        }
    }

    fun onOrderSuccess(orderList: List<Order>) {
        //给adapter设置数据
        orderRvAdapter.setOrderList(orderList)
        srl_order.isRefreshing = false
    }

    fun onOrderFailed() {
        toast("服务器繁忙")
        srl_order.isRefreshing = false
    }
}