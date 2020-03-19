package com.zhan.mvvm.interceptor

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zhan.ktwing.ext.logd
import okhttp3.HttpUrl
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  author: HyJame
 *  date:   2020-03-18
 *  desc:   TODO
 */
@RunWith(AndroidJUnit4::class)
class UrlInterceptorTest {

    @Test
    fun test_base_url_replace() {
        val oldHttpUrl = HttpUrl.parse("https://www.github.com/wiki/part")
        println(oldHttpUrl)

        val newHttpUrl = createNewHttpUrl("https://www.google.com/api", oldHttpUrl!!)
        println(newHttpUrl)
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
}