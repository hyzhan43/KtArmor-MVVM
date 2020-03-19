package com.zhan.mvvm.http.intercept

import androidx.collection.LruCache
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.http.RetrofitFactory
import okhttp3.*

/**
 *  author: HyJame
 *  date:   2020-03-16
 *  desc:   url 拦截器, 动态替换url
 */
class UrlInterceptor : Interceptor {

    private val urlBucket by lazy { RetrofitFactory.urlMap }

    private val URL_KEY = "URL"

    private var pathSize: Int = 0

    private val urlCache by lazy { LruCache<String, String>(100) }

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

        return urlBucket[urlKey]?.let { parseHeaderHttpUrl(it, oldHttpUrl) }
                ?: oldHttpUrl
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


    private fun parseHeaderHttpUrl(headerHttpUrl: HttpUrl?, oldHttpUrl: HttpUrl): HttpUrl? {
        return headerHttpUrl?.let {
            pathSize = it.pathSize()

            if (it.pathSegments().last().isEmpty()) {
                pathSize -= 1
            }

            val newHttpUrl = createHttpUrl(it, oldHttpUrl)

            logd("pageSize = $pathSize, old http url is $oldHttpUrl, new http url is $newHttpUrl")

            newHttpUrl
        }
    }


    private fun createHttpUrl(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl): HttpUrl {
        val builder = createHttpBuilder(headerHttpUrl, oldHttpUrl)

        val newHttpUrl = builder
                .scheme(headerHttpUrl.scheme())
                .host(headerHttpUrl.host())
                .port(headerHttpUrl.port())
                .build()

        updateUrlCache(headerHttpUrl, oldHttpUrl, newHttpUrl)

        return newHttpUrl
    }

    private fun createHttpBuilder(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl): HttpUrl.Builder {
        return getUrlFromCache(headerHttpUrl, oldHttpUrl)?.let { httpUrlCache ->
            oldHttpUrl.newBuilder().apply { encodedPath(httpUrlCache) }
        } ?: realCreateHttpBuilder(headerHttpUrl, oldHttpUrl)
    }

    private fun realCreateHttpBuilder(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl): HttpUrl.Builder {
        val builder = oldHttpUrl.newBuilder()

        for (i in 0 until oldHttpUrl.pathSize()) {
            builder.removePathSegment(0)
        }

        val newPathSegments = arrayListOf<String>()
        newPathSegments.addAll(headerHttpUrl.encodedPathSegments())

        if (oldHttpUrl.pathSize() < pathSize) {
            throw IllegalArgumentException("$headerHttpUrl pathSize more than $oldHttpUrl pathSize")
        }

        val encodedPathSegments = oldHttpUrl.encodedPathSegments()
        for (i in pathSize until encodedPathSegments.size) {
            newPathSegments.add(encodedPathSegments[i])
        }

        for (PathSegment in newPathSegments) {
            builder.addEncodedPathSegment(PathSegment)
        }

        return builder
    }

    private fun updateUrlCache(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl, newHttpUrl: HttpUrl) {
        val key = getKey(headerHttpUrl, oldHttpUrl)
        if (urlCache.get(key) == null) {
            urlCache.put(key, newHttpUrl.encodedPath())
        }
    }

    private fun getUrlFromCache(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl): String? {
        val key = getKey(headerHttpUrl, oldHttpUrl)
        return urlCache.get(key)
    }

    private fun getKey(headerHttpUrl: HttpUrl, oldHttpUrl: HttpUrl): String {
        return "$pathSize-${headerHttpUrl.encodedPath()}-${oldHttpUrl.encodedPath()}"
    }
}

