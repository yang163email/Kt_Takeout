package com.yan.takeout.kt.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.model.db.ReceiptAddressBean
import com.yan.takeout.kt.ui.views.AddressItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/5 18:26
 *  @description : 地址RecyclerView的adapter
 */
class AddressRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list = mutableListOf<ReceiptAddressBean>()

    fun setAddressList(list: List<ReceiptAddressBean>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val addressItemView = holder?.itemView as AddressItemView
        addressItemView.bindView(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            AddressViewHolder(AddressItemView(context))

    class AddressViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}