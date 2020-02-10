package com.zhan.mvvm.http

import com.zhan.mvvm.config.Setting
import com.zhan.mvvm.http.intercept.LoggingIntercept
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
object BaseOkHttpClient {

    // 初始化 okHttp
    fun create(vararg interceptors: Interceptor): OkHttpClient {

        val builder = OkHttpClient.Builder()

        interceptors.forEach {
            builder.addInterceptor(it)
        }

        builder.addInterceptor(LoggingIntercept.init())
                .readTimeout(Setting.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Setting.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(Setting.CONNECT_TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }
}