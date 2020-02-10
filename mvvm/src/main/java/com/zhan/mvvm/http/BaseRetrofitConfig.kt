package com.zhan.mvvm.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
abstract class BaseRetrofitConfig : RetrofitConfig {

    lateinit var baseUrl: String

    override fun initRetrofit(): Retrofit = BaseRetrofit.create(baseUrl)

    override fun initOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        return BaseOkHttpClient.create(*interceptors)
    }
}