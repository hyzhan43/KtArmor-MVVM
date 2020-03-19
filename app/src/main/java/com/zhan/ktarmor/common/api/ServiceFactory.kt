package com.zhan.ktarmor.common.api

import com.zhan.ktarmor.common.TestServiceConfig
import com.zhan.ktarmor.common.interceptor.TestInterceptor
import com.zhan.mvvm.http.RetrofitFactory
import com.zhan.mvvm.mvvm.BaseRepository

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
object ServiceFactory {

    val apiService by lazy { RetrofitFactory.create(ApiService::class.java) }

    val testApiService by lazy { RetrofitFactory.create(TestApiService::class.java, TestServiceConfig()) }
}