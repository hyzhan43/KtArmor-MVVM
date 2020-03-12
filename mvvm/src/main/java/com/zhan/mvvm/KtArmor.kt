package com.zhan.mvvm

import android.app.Application
import com.zhan.ktwing.KtWing
import com.zhan.mvvm.delegate.ActivityLifecycle
import com.zhan.mvvm.http.BaseRetrofitConfig
import com.zhan.mvvm.http.DefaultRetrofitConfig

object KtArmor {

    var retrofitConfig: BaseRetrofitConfig = DefaultRetrofitConfig()

    internal fun init(application: Application) {
        KtWing.init(application)
        application.registerActivityLifecycleCallbacks(ActivityLifecycle)
    }

    fun initRetrofitConfig(retrofitConfig: BaseRetrofitConfig) {
        this.retrofitConfig = retrofitConfig
    }
}