package com.yan.takeout.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.ui.views.HomeCommonItemView
import com.yan.takeout.ui.views.HomeSellerItemView
import com.yan.takeout.ui.views.HomeTitleItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 21:03
 *  @description : home界面RecyclerView的adapter
 */
class HomeRvAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TYPE_TITLE = 0
        val TYPE_SELLER = 1
    }

    private val mDatas = mutableListOf<String>()

    fun setDatas(datas: List<String>) {
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) TYPE_TITLE
        else TYPE_SELLER

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            TYPE_TITLE -> {
                val homeTitleItemView = holder?.itemView as HomeTitleItemView
                homeTitleItemView.bindView("我是大哥---------------------")
            }
            TYPE_SELLER -> {
                val homeSellerItemView = holder?.itemView as HomeSellerItemView
                homeSellerItemView.bindView(mDatas[position-1])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_TITLE -> HomeTitleViewHolder(HomeTitleItemView(context))
            TYPE_SELLER -> HomeSellerViewHolder(HomeSellerItemView(context))
            else -> HomeTitleViewHolder(HomeCommonItemView(context))
        }

    override fun getItemCount(): Int =
        if ((mDatas.size > 0)) mDatas.size + 1
        else 0

    class HomeTitleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class HomeSellerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}