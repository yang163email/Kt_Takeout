package com.yan.takeout.kt.model.net

import com.yan.takeout.kt.utils.Constants
import dagger.internal.DoubleCheck.lazy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import java.util.logging.Logger

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/6 17:46
 *  @description : Retrofit 网络请求统一管理类
 */
class HttpManager private constructor() {

    var takeoutService: TakeoutService

    init {
        takeoutService = Retrofit.Builder()
                .baseUrl(Constants.DOMAIN)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //添加gson转换工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加rxjava适配工厂
                .build()
                .create<TakeoutService>(TakeoutService::class.java)
    }

    companion object {
        val instance by lazy { HttpManager() }
    }

    private fun createOkHttpClient(): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor()
        val loggingInterceptor = HttpLoggingInterceptor {
//            Platform.get().log(Platform.WARN, it, null)   //OkHttp打印框架
            Logger.getLogger("OkHttp").log(Level.WARNING, it)   //java内置打印框架
        }
//        val loggingInterceptor = HttpLoggingInterceptor {
////            Timber.tag("OkHttp").e(it)    //timber日志框架
//            Logger.t("OkHttp").e(it)    //logger日志框架
//        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addNetworkInterceptor(loggingInterceptor)
                .build()
    }

}