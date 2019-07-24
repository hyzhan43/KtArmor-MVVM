package com.zhan.mvvm.http

import com.zhan.mvvm.KtArmor
import retrofit2.Retrofit

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */

class RetrofitFactory private constructor() {

    companion object {
        val instance by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit by lazy {
        KtArmor.retrofit.initRetrofit()
    }

    fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz)
    }
}