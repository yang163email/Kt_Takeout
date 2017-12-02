package com.yan.takeout.kt.di.component

import com.yan.takeout.kt.di.module.GoodsFragmentModule
import com.yan.takeout.kt.ui.fragment.GoodsFragment
import dagger.Component

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/2 17:20
 *  @description : TODO
 */
@Component(modules = [GoodsFragmentModule::class])
interface GoodsFragmentComponent {
    fun inject(goodsFragment: GoodsFragment)
}