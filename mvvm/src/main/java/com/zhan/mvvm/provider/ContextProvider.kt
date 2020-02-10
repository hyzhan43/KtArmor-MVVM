package com.zhan.mvvm.provider

import android.app.Application
import android.content.Context
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.http.BaseRetrofitConfig
import com.zhan.mvvm.http.DefaultRetrofitConfig

/**
 *  author: HyJame
 *  date:   2019-12-04
 *  desc:   默认初始化 KtArmor, 并提供 全局 applicationContext
 */
object ContextProvider {

    lateinit var application: Application

    fun attachContext(context: Context?) {
        this.application = context as? Application ?: throw RuntimeException("init KtArmor error !")

        // init KtArmor
        KtArmor.init(application)

        logd("KtArmor init success !")
    }
}