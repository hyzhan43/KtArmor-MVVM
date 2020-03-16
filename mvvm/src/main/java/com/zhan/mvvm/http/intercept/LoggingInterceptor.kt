package com.zhan.mvvm.http.intercept

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
object LoggingInterceptor {

    fun init(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}