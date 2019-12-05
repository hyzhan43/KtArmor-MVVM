package com.zhan.mvvm

import android.app.Application
import com.zhan.ktwing.KtWing
import com.zhan.mvvm.delegate.ActivityLifecycle
import com.zhan.mvvm.http.RetrofitConfig

object KtArmor {

    lateinit var retrofit: RetrofitConfig

    fun init(application: Application, retrofit: RetrofitConfig) {
        this.retrofit = retrofit
        KtWing.init(application)

        application.registerActivityLifecycleCallbacks(ActivityLifecycle)
    }
}