package com.zhan.ktarmor.common.interceptor

import com.zhan.ktarmor.common.api.API
import com.zhan.ktwing.common.Preference
import okhttp3.Interceptor

/**
 * author：  HyZhan
 * create：  2019/8/14
 * desc：    TODO
 */
object CookieInterceptor {

    // 解析 cookie, 并存储
    var cookie by Preference("cookie", "")

    // cookie 拦截器 获取 cookie (自动登录时候需要用到)
    fun create(): Interceptor {
        return Interceptor { chain ->
            // 获取 请求
            val request = chain.request()
            val requestUrl = request.url().toString()

            // 如果 是(登录请求 或者 注册请求) 并且 请求头包含 cookie
            if ((requestUrl.contains(API.LOGIN) || requestUrl.contains(API.REGISTER))) {
                val response = chain.proceed(request)
                // 获取 全部 cookie
                val cookies = response.headers("set-cookie")


                cookie = cookies.joinToString(";")

                return@Interceptor response
            } else {
                val builder = request.newBuilder()
                if (cookie.isNotEmpty()) {
                    builder.addHeader("Cookie", cookie)
                }

                return@Interceptor chain.proceed(builder.build())
            }
        }
    }
}