package com.yan.takeout.di.component

import com.yan.takeout.di.module.OrderFragmentModule
import com.yan.takeout.ui.fragment.OrderFragment
import dagger.Component

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 20:30
 *  @description : TODO
 */
@Component(modules = [(OrderFragmentModule::class)])
interface OrderFragmentComponent {
    fun inject(orderFragment: OrderFragment)
}