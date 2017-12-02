package com.yan.takeout.kt.di.module

import com.yan.takeout.kt.presenter.LoginActivityPresenter
import com.yan.takeout.kt.ui.activity.LoginActivity
import dagger.Module
import dagger.Provides

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:26
 *  @description : TODO
 */
@Module
class LoginActivityModule(val loginActivity: LoginActivity) {
    @Provides
    fun provideLoginActivityPresenter() =
        LoginActivityPresenter(loginActivity)
}