package com.zhan.mvvm

import android.content.Context
import com.zhan.mvvm.utils.Preference
import com.zhan.mvvm.ext.Toasts
import com.zhan.mvvm.http.RetrofitConfig

object KtArmor {

    lateinit var retrofit: RetrofitConfig

    fun init(context: Context, retrofit: RetrofitConfig) {

        this.retrofit = retrofit

        // 初始化 SharePreference
        Preference.init(context)
        // 初始化 Toast
        Toasts.init(context)
    }
}