package com.yan.takeout.kt.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.R
import com.yan.takeout.kt.di.component.DaggerGoodsFragmentComponent
import com.yan.takeout.kt.di.module.GoodsFragmentModule
import com.yan.takeout.kt.model.beans.GoodsInfo
import com.yan.takeout.kt.model.beans.GoodsTypeInfo
import com.yan.takeout.kt.presenter.GoodsFragmentPresenter
import com.yan.takeout.kt.ui.adapter.GoodsAdapter
import com.yan.takeout.kt.ui.adapter.GoodsTypeRvAdapter
import kotlinx.android.synthetic.main.fragment_goods.*
import javax.inject.Inject

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:26
 *  @description : 商品fragment
 */
class GoodsFragment : Fragment() {
    @Inject
    lateinit var goodsPresenter: GoodsFragmentPresenter
    private lateinit var goodsAdapter: GoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        DaggerGoodsFragmentComponent.builder()
                .goodsFragmentModule(GoodsFragmentModule(this))
                .build()
                .inject(this)
        rv_goods_type.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = GoodsTypeRvAdapter(activity)
        }
        goodsAdapter = GoodsAdapter(activity)
        slhlv.adapter = goodsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        goodsPresenter.getBusinessInfo("1")
    }

    fun onLoadBusinessSuccess(goodsTypeList: List<GoodsTypeInfo>) {
        (rv_goods_type.adapter as GoodsTypeRvAdapter).setData(goodsTypeList)

        val goodsList = mutableListOf<GoodsInfo>()
        goodsTypeList.forEach { outer->
            val list = outer.list
            list.forEach { inner->
                //遍历，对每个商品对应typeId，typeName进行赋值
                inner.typeId = outer.id
                inner.typeName = outer.name
            }
            goodsList.addAll(list)
        }
        goodsAdapter.setData(goodsList)
    }
}