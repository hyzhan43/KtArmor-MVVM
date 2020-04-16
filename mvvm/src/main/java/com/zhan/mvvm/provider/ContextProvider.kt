package com.zhan.mvvm.provider

import android.app.Application
import android.content.Context
import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.log.PrintLog

/**
 *  author: HyJame
 *  date:   2019-12-04
 *  desc:   默认初始化 KtArmor, 并提供 全局 applicationContext
 */
object ContextProvider {

    lateinit var application: Application

    fun attachContext(context: Context?) {
        this.application = context as? Application ?: throw Throwable("init KtArmor error !")

        // init KtArmor
        KtArmor.init(application)

        PrintLog.log("KtArmor init success !")
    }
}