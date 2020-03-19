package com.zhan.mvvm.http

import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.annotation.BaseUrl
import com.zhan.mvvm.annotation.BindUrls
import com.zhan.mvvm.http.intercept.UrlInterceptor
import okhttp3.HttpUrl
import java.util.HashMap

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    retrofit 工厂类
 */
object RetrofitFactory {

    val urlMap = hashMapOf<String, HttpUrl?>()

    fun <T> create(clz: Class<T>, retrofitConfig: BaseRetrofitConfig? = null): T {
        val baseUrl = prepareBaseUrl(clz)
        prepareOtherUrls(clz)

        // 判断是否, 单独设置了 retrofitConfig, 否则默认按照全局 RetrofitConfig 配置
        val retrofit = retrofitConfig?.initRetrofit(baseUrl)
                ?: KtArmor.retrofitConfig.initRetrofit(baseUrl)

        return retrofit.create(clz)
    }

    private fun <T> prepareOtherUrls(clz: Class<T>) {
        clz.getAnnotation(BindUrls::class.java)?.values
                ?.filter { it.isNotEmpty() }
                ?.forEach { url -> urlMap[url] = HttpUrl.parse(url) }
    }

    private fun <T> prepareBaseUrl(clz: Class<T>): String {
        val baseUrlAnnotation = clz.getAnnotation(BaseUrl::class.java)
        return baseUrlAnnotation?.value ?: throw IllegalArgumentException("base url is null")
    }
}