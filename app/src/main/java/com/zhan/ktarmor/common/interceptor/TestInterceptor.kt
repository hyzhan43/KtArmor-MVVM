package com.zhan.ktarmor.common.interceptor

import com.zhan.ktwing.ext.logd
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  author: HyJame
 *  date:   2020-03-19
 *  desc:   TODO
 */
object TestInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        logd("我是 testService 单独拦截器")
        return chain.proceed(chain.request())
    }
}