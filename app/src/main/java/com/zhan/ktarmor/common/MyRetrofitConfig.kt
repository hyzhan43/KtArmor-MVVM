package com.zhan.ktarmor.common

import com.zhan.ktarmor.common.interceptor.CookieInterceptor
import com.zhan.mvvm.http.BaseRetrofitConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
class MyRetrofitConfig : BaseRetrofitConfig() {

    override fun initOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return super.initOkHttpClient(CookieInterceptor.create())
    }
}