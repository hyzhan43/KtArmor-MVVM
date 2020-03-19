package com.zhan.ktarmor.common

import com.zhan.ktarmor.common.interceptor.TestInterceptor
import com.zhan.mvvm.http.BaseOkHttpClient
import com.zhan.mvvm.http.BaseRetrofitConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 *  author: HyJame
 *  date:   2020-03-19
 *  desc:   TODO
 */
class TestServiceConfig : BaseRetrofitConfig() {

    override fun initOkHttpClient(): OkHttpClient {
        return BaseOkHttpClient.create(TestInterceptor)
    }
}