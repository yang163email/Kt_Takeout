package com.yan.takeout.di.component

import com.yan.takeout.di.module.LoginActivityModule
import com.yan.takeout.ui.activity.LoginActivity
import dagger.Component

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:28
 *  @description : TODO
 */
@Component(modules = arrayOf(LoginActivityModule::class))
interface LoginActivityComponent {
    fun inject(loginActivity: LoginActivity)
}