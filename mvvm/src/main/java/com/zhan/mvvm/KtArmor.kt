package com.zhan.mvvm

import android.content.Context
import com.zhan.ktwing.KtWing
import com.zhan.mvvm.http.RetrofitConfig

object KtArmor {

    lateinit var retrofit: RetrofitConfig

    fun init(context: Context, retrofit: RetrofitConfig) {

        this.retrofit = retrofit

        KtWing.init(context)
    }
}