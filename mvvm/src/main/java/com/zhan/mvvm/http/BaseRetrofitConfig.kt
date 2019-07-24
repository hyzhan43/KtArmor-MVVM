package com.zhan.mvvm.http

import com.zhan.mvvm.config.Setting
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * author：  HyZhan
 * create：  2019/7/24
 * desc：    TODO
 */
abstract class BaseRetrofitConfig : RetrofitConfig {

    override val readTimeOut: Long
        get() = Setting.READ_TIME_OUT

    override val writeTimeOut: Long
        get() = Setting.WRITE_TIME_OUT

    override val connectTimeOut: Long
        get() = Setting.CONNECT_TIME_OUT

    override fun initRetrofit(): Retrofit = BaseRetrofit.create()

    override fun initOkHttpClient(): OkHttpClient = BaseOkHttpClient.create()
}