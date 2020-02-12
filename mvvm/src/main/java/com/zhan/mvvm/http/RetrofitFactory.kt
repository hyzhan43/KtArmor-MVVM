package com.zhan.mvvm.http

import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.annotation.BaseUrl
import retrofit2.Retrofit
import java.lang.IllegalArgumentException

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    retrofit 工厂类
 */

object RetrofitFactory {

    fun <T> create(clz: Class<T>): T {
        prepareBaseUrl(clz)

        val retrofit = KtArmor.retrofitConfig.initRetrofit()

        return retrofit.create(clz)
    }

    private fun <T> prepareBaseUrl(clz: Class<T>) {
        val baseUrlAnnotation = clz.getAnnotation(BaseUrl::class.java)
        val baseUrl = baseUrlAnnotation?.value ?: throw IllegalArgumentException("base url is null")
        KtArmor.retrofitConfig.baseUrl = baseUrl
    }
}