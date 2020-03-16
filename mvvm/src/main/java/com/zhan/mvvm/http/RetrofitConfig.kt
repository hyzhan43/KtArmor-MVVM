package com.zhan.mvvm.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
interface RetrofitConfig {

    fun initRetrofit(baseUrl: String): Retrofit

    fun initOkHttpClient(vararg interceptors: Interceptor): OkHttpClient
}