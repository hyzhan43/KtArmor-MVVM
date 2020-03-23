package com.zhan.mvvm

import android.app.Application
import com.zhan.ktwing.KtWing
import com.zhan.mvvm.delegate.ActivityLifecycle
import com.zhan.mvvm.http.BaseRetrofitConfig
import com.zhan.mvvm.http.DefaultRetrofitConfig
import com.zhan.mvvm.mvvm.actuator.ActivityActuator
import com.zhan.mvvm.mvvm.actuator.LiveDataActuator

object KtArmor {

    var retrofitConfig: BaseRetrofitConfig = DefaultRetrofitConfig()
    lateinit var liveDataActuator: LiveDataActuator<*>
    lateinit var activityActuator: ActivityActuator<*>

    internal fun init(application: Application) {
        KtWing.init(application)
        application.registerActivityLifecycleCallbacks(ActivityLifecycle)
    }

    fun initRetrofitConfig(retrofitConfig: BaseRetrofitConfig) {
        this.retrofitConfig = retrofitConfig
    }

    fun configActivityActuator() {

    }
}