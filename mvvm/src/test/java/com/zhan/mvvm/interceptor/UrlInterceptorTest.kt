package com.zhan.mvvm.interceptor

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zhan.ktwing.ext.logd
import okhttp3.HttpUrl
import org.junit.Test
import org.junit.runner.RunWith
import android.R.attr.scheme
import android.text.TextUtils


/**
 *  author: HyJame
 *  date:   2020-03-18
 *  desc:   TODO
 */
@RunWith(AndroidJUnit4::class)
class UrlInterceptorTest {

    var pathSize = 1

    @Test
    fun test_base_url_replace() {
        val oldHttpUrl = HttpUrl.parse("https://www.github.com/wiki/part/a/b")

        val domainUrl = HttpUrl.parse("https://www.google.com/api")
        pathSize = domainUrl?.pathSize() ?: 0
        println(pathSize)

        val newHttpUrl = parseUrl(domainUrl, oldHttpUrl!!)

        println("oldHttpUrl = $oldHttpUrl")
        println("newHttpUrl = $newHttpUrl")
    }

    @Test
    fun test_base_url_replace2() {
        val oldHttpUrl = HttpUrl.parse("https://www.github.com/wiki/part")

        val domainUrl = HttpUrl.parse("https://www.google.com")

        val newHttpUrl = parseUrl(domainUrl, oldHttpUrl!!)

        println("oldHttpUrl = $oldHttpUrl")
        println("newHttpUrl = $newHttpUrl")
    }

    @Test
    fun test_base_url_replace3() {
        pathSize = 0

        val oldHttpUrl = HttpUrl.parse("https://www.github.com/wiki/part")

        val domainUrl = HttpUrl.parse("https://www.google.com/api")

        val newHttpUrl = parseUrl(domainUrl, oldHttpUrl!!)

        println("oldHttpUrl = $oldHttpUrl")
        println("newHttpUrl = $newHttpUrl")
    }


    fun parseUrl(domainUrl: HttpUrl?, url: HttpUrl): HttpUrl {
        if (null == domainUrl) return url

        val builder = url.newBuilder()

        for (i in 0 until url.pathSize()) {
            //当删除了上一个 index, PathSegment 的 item 会自动前进一位, 所以 remove(0) 就好
            builder.removePathSegment(0)
        }

        val newPathSegments = arrayListOf<String>()
        newPathSegments.addAll(domainUrl.encodedPathSegments())

        if (url.pathSize() > pathSize) {
            val encodedPathSegments = url.encodedPathSegments()
            for (i in pathSize until encodedPathSegments.size) {
                newPathSegments.add(encodedPathSegments[i])
            }
        } else if (url.pathSize() < pathSize) {
            // TODO 报错
        }

        for (PathSegment in newPathSegments) {
            builder.addEncodedPathSegment(PathSegment)
        }

        return builder
                .scheme(domainUrl.scheme())
                .host(domainUrl.host())
                .port(domainUrl.port())
                .build()
    }
}