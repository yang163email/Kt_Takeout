package com.yan.takeout.kt.di.component

import com.yan.takeout.kt.di.module.HomeFragmentModule
import com.yan.takeout.kt.ui.fragment.HomeFragment
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