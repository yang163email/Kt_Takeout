package com.yan.takeout.kt.di.component

import com.yan.takeout.kt.di.module.LoginActivityModule
import com.yan.takeout.kt.ui.activity.LoginActivity
import dagger.Component

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/1 14:28
 *  @description : TODO
 */
@Component(modules = [(LoginActivityModule::class)])
interface LoginActivityComponent {
    fun inject(loginActivity: LoginActivity)
}