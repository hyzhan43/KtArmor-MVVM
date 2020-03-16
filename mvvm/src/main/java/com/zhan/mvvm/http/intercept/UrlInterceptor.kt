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

    private var urlBucket: HashMap<String, String>? = null

    private val URL_KEY = "URL"

    companion object {
        const val URL_PREFIX = "URL: "
    }

    init {
        urlBucket = RetrofitFactory.urlMap
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

        return urlBucket?.let { urlBucket ->


            urlBucket[urlKey]?.let { url ->
                val httpUrl = request.url()

                val oldUrl = "${httpUrl.scheme()}://${httpUrl.host()}"

                val newUrl = httpUrl
                        .url()
                        .toString()
                        .replace(oldUrl, url)

                newBuilder.url(newUrl).build()
            } ?: newBuilder.url(request.url()).build()
        } ?: newBuilder.url(request.url()).build()
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