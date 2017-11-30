package com.yan.takeout.di.module

import com.yan.takeout.presenter.HomeFragmentPresenter
import com.yan.takeout.ui.fragment.HomeFragment
import dagger.Module
import dagger.Provides

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 19:00
 *  @description : TODO
 */
@Module
class HomeFragmentModule(val homeFragment: HomeFragment) {
    @Provides
    fun provideHomeFragmentPresenter() =
            HomeFragmentPresenter(homeFragment)
}