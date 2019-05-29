package com.zhan.mvvm

import android.content.Context
import com.zhan.mvvm.common.Preference
import com.zhan.mvvm.config.Setting

/**
 * @author  hyzhan
 * @date    2019/5/28
 * @desc    TODO
 */
class KtArmor private constructor(builder: Builder) {

    companion object {
        lateinit var BASE_URL: String
        var CONNECT_TIME_OUT: Long = 0
        var READ_TIME_OUT: Long = 0
        var WRITE_TIME_OUT: Long = 0
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    init {
        BASE_URL = builder.baseUrl
        CONNECT_TIME_OUT = builder.connectTime
        READ_TIME_OUT = builder.readTime
        WRITE_TIME_OUT = builder.writeTime
        // 初始化 SharePreference
        Preference.init(builder.context)
    }

    class Builder {
        lateinit var context: Context
        lateinit var baseUrl: String
        var connectTime = Setting.CONNECT_TIME_OUT
        var readTime = Setting.READ_TIME_OUT
        var writeTime = Setting.WRITE_TIME_OUT

        fun build() = KtArmor(this)
    }
}