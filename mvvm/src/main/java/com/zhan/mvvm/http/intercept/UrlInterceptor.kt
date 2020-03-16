package com.zhan.mvvm.http.intercept

import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.http.RetrofitFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import android.R.attr.scheme
import okhttp3.HttpUrl
import android.R.attr.scheme
import android.text.TextUtils


/**
 *  author: HyJame
 *  date:   2020-03-16
 *  desc:   TODO
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

    /**
     *  判断 urlBucket 是否有值, 否则 直接请求
     *  判断 urlKey 是否存在, 否则 直接请求
     *
     */
    private fun processRequest(request: Request): Request {
        val newBuilder = request.newBuilder()

        val urlKey = getUrlFromHeader(request) ?: return newBuilder.url(request.url()).build()

        val httpUrl = urlBucket[urlKey]?.let { getNewHttpUrl(request, it) } ?: request.url()

        return newBuilder.url(httpUrl).build()
    }

    private fun getNewHttpUrl(request: Request, url: String): HttpUrl {
        val httpUrl = request.url()

        val headerHttpUrl = HttpUrl.parse(url)

        return headerHttpUrl?.let {
            httpUrl.newBuilder()
                    .scheme(it.scheme())
                    .host(it.host())
                    .port(it.port())
                    .build()
        } ?: httpUrl
    }

    private fun getUrlFromHeader(request: Request): String? {
        val headers = request.headers(URL_KEY)

        if (headers.size == 0)
            return null

        if (headers.size > 1)
            throw IllegalArgumentException("Only one URL_PREFIX in the headers")

        return request.header(URL_KEY)
    }
}