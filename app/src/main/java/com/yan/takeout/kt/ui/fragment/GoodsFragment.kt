package com.yan.takeout.kt.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.takeout.kt.R
import com.yan.takeout.kt.di.component.DaggerGoodsFragmentComponent
import com.yan.takeout.kt.di.module.GoodsFragmentModule
import com.yan.takeout.kt.presenter.GoodsFragmentPresenter
import javax.inject.Inject

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 16:26
 *  @description : 商品fragment
 */
class GoodsFragment : Fragment() {
    @Inject
    lateinit var goodsPresenter: GoodsFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        DaggerGoodsFragmentComponent.builder()
                .goodsFragmentModule(GoodsFragmentModule(this))
                .build()
                .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        goodsPresenter.getBusinessInfo("1")
    }
}