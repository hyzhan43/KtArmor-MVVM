package com.zhan.mvvm.http

import com.zhan.mvvm.KtArmor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
object BaseRetrofit {

    fun create(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(KtArmor.retrofit.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(KtArmor.retrofit.initOkHttpClient())
                .build()
    }
}