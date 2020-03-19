package com.zhan.mvvm.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
object BaseRetrofit {

    fun create(baseUrl: String, retrofitConfig: BaseRetrofitConfig): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(retrofitConfig.initOkHttpClient())
                .build()
    }
}