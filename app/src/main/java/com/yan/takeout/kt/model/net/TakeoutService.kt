package com.yan.takeout.kt.model.net

import com.yan.takeout.kt.model.beans.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 13:41
 *  @description : 所有网络接口
 */
interface TakeoutService {
    @GET("home")
    fun getHomeInfo(): Call<ResponseInfo>

    @GET("login")
    fun loginByPhone(@Query("phone") phone: String): Call<ResponseInfo>

    @GET("order")
    fun getOrderList(@Query("id") userId: String): Call<ResponseInfo>
}