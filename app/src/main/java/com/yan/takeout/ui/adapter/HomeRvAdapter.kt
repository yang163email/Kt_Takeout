package com.yan.takeout.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.ui.views.HomeCommonItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/29 21:03
 *  @description : home界面RecyclerView的adapter
 */
class HomeRvAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mDatas = mutableListOf<String>()

    fun setDatas(datas: List<String>) {
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val homeCommonItemView = holder?.itemView as HomeCommonItemView
        homeCommonItemView.bindView(mDatas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        HomeRvViewHolder(HomeCommonItemView(context))

    override fun getItemCount(): Int = mDatas.size

    class HomeRvViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}