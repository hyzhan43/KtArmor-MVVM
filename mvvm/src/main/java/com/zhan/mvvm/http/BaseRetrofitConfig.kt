package com.zhan.mvvm.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
abstract class BaseRetrofitConfig : RetrofitConfig {

    override fun initRetrofit(baseUrl: String): Retrofit = BaseRetrofit.create(baseUrl, this)

    override fun initOkHttpClient(): OkHttpClient = BaseOkHttpClient.create()
}