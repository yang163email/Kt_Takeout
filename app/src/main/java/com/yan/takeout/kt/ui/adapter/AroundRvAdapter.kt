package com.yan.takeout.kt.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.amap.api.services.core.PoiItem
import com.yan.takeout.kt.ui.views.AroundItemView
import kotlinx.android.synthetic.main.item_around_address.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/6 11:52
 *  @description : 周边RecyclerView的adapter
 */
class AroundRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val poiList = mutableListOf<PoiItem>()

    fun setPoiList(list: List<PoiItem>) {
        poiList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val aroundItemView = holder?.itemView as AroundItemView
        aroundItemView.bindView(poiList[position])
        aroundItemView.setOnClickListener {
            val data = Intent()
            data.putExtra("title", aroundItemView.tv_title.text)
            data.putExtra("address", aroundItemView.tv_address.text)
            (context as Activity).apply {
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }
    }

    override fun getItemCount(): Int = poiList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            AroundViewHolder(AroundItemView(context))

    class AroundViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}