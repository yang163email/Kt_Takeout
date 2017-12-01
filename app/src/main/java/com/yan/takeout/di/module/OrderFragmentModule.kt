package com.yan.takeout.di.module

import com.yan.takeout.presenter.OrderFragmentPresenter
import com.yan.takeout.ui.fragment.OrderFragment
import dagger.Module
import dagger.Provides

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 20:28
 *  @description : TODO
 */
@Module
class OrderFragmentModule(val orderFragment: OrderFragment) {
    @Provides
    fun provideOrderFragmentPresenter() =
            OrderFragmentPresenter(orderFragment)
}