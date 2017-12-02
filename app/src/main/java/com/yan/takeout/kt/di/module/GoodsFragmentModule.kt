package com.yan.takeout.kt.di.module

import com.yan.takeout.kt.presenter.GoodsFragmentPresenter
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import dagger.Module
import dagger.Provides

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 17:15
 *  @description : TODO
 */
@Module
class GoodsFragmentModule(val goodsFragment: GoodsFragment) {
    @Provides
    fun provideGoodsFragmentPresenter() =
            GoodsFragmentPresenter(goodsFragment)
}