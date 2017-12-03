package com.yan.takeout.kt.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import com.yan.takeout.kt.ui.views.GoodsTypeItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/3 8:38
 *  @description : 商品类型adapter
 */
class GoodsTypeRvAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val goodsTypeList = mutableListOf<GoodsTypeInfo>()

    fun setData(list: List<GoodsTypeInfo>) {
        goodsTypeList.clear()
        goodsTypeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val goodsTypeItemView = holder?.itemView as GoodsTypeItemView
        goodsTypeItemView.bindView(goodsTypeList[position])
    }

    override fun getItemCount(): Int = goodsTypeList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            GoodsTypeViewHolder(GoodsTypeItemView(context))

    class GoodsTypeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}