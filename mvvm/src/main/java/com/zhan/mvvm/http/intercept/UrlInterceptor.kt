package com.zhan.mvvm.http.intercept

import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.http.RetrofitFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 *  author: HyJame
 *  date:   2020-03-16
 *  desc:   url 拦截器, 动态替换url
 */
class UrlInterceptor : Interceptor {

    private val urlBucket by lazy { RetrofitFactory.urlMap }

    private val URL_KEY = "URL"

    companion object {
        const val URL_PREFIX = "URL: "
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO 开关
        return chain.proceed(processRequest(chain.request()))
    }

    private fun processRequest(request: Request): Request {
        return request.newBuilder()
                .url(getHttpUrl(request))
                .build()
    }

    private fun getHttpUrl(request: Request): HttpUrl {
        val urlKey = getUrlFromHeader(request)

        val oldHttpUrl = request.url()

        return urlBucket[urlKey]?.let { createNewHttpUrl(it, oldHttpUrl) } ?: oldHttpUrl
    }

    /**
     *  根据 header 标示的 url, 创建新的 httpUrl
     */
    private fun createNewHttpUrl(url: String, oldHttpUrl: HttpUrl): HttpUrl? = HttpUrl.parse(url)?.let { newHttpUrl ->

        logd("new http url is $newHttpUrl, old http url is $oldHttpUrl")

        return oldHttpUrl.newBuilder()
                .scheme(newHttpUrl.scheme())
                .host(newHttpUrl.host())
                .port(newHttpUrl.port())
                .build()
    }

    /**
     *  判断是否有 @Header 注解, 并且有 URL: www.xxx.com 标示
     */
    private fun getUrlFromHeader(request: Request): String? {
        val headers = request.headers(URL_KEY)

        if (headers.size == 0)
            return null

        if (headers.size > 1)
            throw IllegalArgumentException("Only one URL_PREFIX in the headers")

        return request.header(URL_KEY)
    }
}

