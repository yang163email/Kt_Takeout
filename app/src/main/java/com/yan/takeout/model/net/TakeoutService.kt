package com.yan.takeout.model.net

import com.yan.takeout.model.beans.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/30 13:41
 *  @description : 所有网络接口
 */
interface TakeoutService {
    @GET("home")
    fun getHomeInfo(): Call<ResponseInfo>
}