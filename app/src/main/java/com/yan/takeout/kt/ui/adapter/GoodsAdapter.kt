package com.yan.takeout.kt.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.yan.takeout.kt.model.beans.GoodsInfo
import com.yan.takeout.kt.ui.views.GoodsItemView
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/3 9:06
 *  @description : 商品adapter
 */
class GoodsAdapter(val context: Context): BaseAdapter(), StickyListHeadersAdapter {

    private val goodsList = mutableListOf<GoodsInfo>()

    fun setData(goodsInfoList: List<GoodsInfo>) {
        goodsList.clear()
        goodsList.addAll(goodsInfoList)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val goodsItemHolder: GoodsItemHolder
        if (convertView == null) {
            itemView = GoodsItemView(context)
            goodsItemHolder = GoodsItemHolder(itemView)
            itemView.tag = goodsItemHolder
        } else {
            itemView = convertView
            goodsItemHolder = itemView.tag as GoodsItemHolder
        }
        val goodsItemView = goodsItemHolder.itemView as GoodsItemView
        goodsItemView.bindView(goodsList[position])
        return itemView
    }

    override fun getItem(position: Int): Any = goodsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = goodsList.size

    override fun getHeaderId(position: Int): Long = 0

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
        textView.text = "我是商品类别1"
        return textView
    }

    class GoodsItemHolder(val itemView: View)
}