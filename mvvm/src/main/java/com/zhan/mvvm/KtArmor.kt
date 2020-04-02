package com.zhan.mvvm

import android.app.Application
import com.zhan.ktwing.KtWing
import com.zhan.mvvm.delegate.ActivityLifecycle
import com.zhan.mvvm.http.BaseRetrofitConfig
import com.zhan.mvvm.http.DefaultRetrofitConfig
import com.zhan.mvvm.mvvm.actuator.*
import com.zhan.mvvm.mvvm.actuator.impl.DefaultActivityActuator
import com.zhan.mvvm.mvvm.actuator.impl.DefaultLiveDataActuator

object KtArmor {

    var retrofitConfig: BaseRetrofitConfig = DefaultRetrofitConfig()

    var activityActuator: IActivityActuator? = null

    var liveDataActuator: ILiveDataActuator? = null

    var printLog = false

    internal fun init(application: Application) {
        KtWing.init(application)
        application.registerActivityLifecycleCallbacks(ActivityLifecycle)
    }

    fun initRetrofitConfig(retrofitConfig: BaseRetrofitConfig) {
        this.retrofitConfig = retrofitConfig
    }

    fun configActivityActuator(actuator: IActivityActuator) {
        this.activityActuator = actuator
    }

    fun configLiveDataActuator(actuator: ILiveDataActuator) {
        this.liveDataActuator = actuator
    }
}