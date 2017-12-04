package com.yan.takeout.kt.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.model.beans.GoodsInfo
import com.yan.takeout.kt.ui.views.CartItemView
import com.yan.takeout.kt.utils.EventBusTag
import org.simple.eventbus.EventBus

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/4 11:51
 *  @description : 购物车RecyclerView的adapter
 */
class CartRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val cartList = mutableListOf<GoodsInfo>()

    fun setCartList(cartList: List<GoodsInfo>) {
        this.cartList.clear()
        this.cartList.addAll(cartList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val cartItemView = holder?.itemView as CartItemView
        cartItemView.bindView(cartList[position])
        cartItemView.setOnItemClickListener { view, goodsInfo ->
            //刷新goodsInfo数据
            goodsInfo.count++
            //刷新购物车内部数量与价格
            notifyDataSetChanged()
            //左侧红点

            //右侧列表
            EventBus.getDefault().post(-1, EventBusTag.TAG_UPDATE_GOODS_INFO)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            CartViewHolder(CartItemView(context))

    override fun getItemCount(): Int = cartList.size

    class CartViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}