package com.yan.takeout.di.component

import com.yan.takeout.di.module.HomeFragmentModule
import com.yan.takeout.ui.fragment.HomeFragment
import dagger.Component

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 19:03
 *  @description : TODO
 */
@Component(modules = [(HomeFragmentModule::class)])
interface HomeFragmentComponent {

    fun inject(homeFragment: HomeFragment)
}