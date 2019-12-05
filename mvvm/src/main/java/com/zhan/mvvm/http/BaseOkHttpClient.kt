package com.zhan.mvvm.http

import com.zhan.mvvm.KtArmor
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
                .readTimeout(KtArmor.retrofit.readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(KtArmor.retrofit.writeTimeOut, TimeUnit.SECONDS)
                .connectTimeout(KtArmor.retrofit.connectTimeOut, TimeUnit.SECONDS)

        return builder.build()
    }
}