package com.zhan.mvvm.mvvm

import androidx.lifecycle.LifecycleOwner
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.mvvm.actuator.CommonLiveData
import com.zhan.mvvm.mvvm.actuator.ActivityActuator

/**
 * author：  HyZhan
 * create：  2019/8/7
 * desc：    TODO
 */
interface IMvmActivity : IActivity, IMvmView {

    fun dataObserver() {}

    fun <R> quickObserve(liveData: CommonLiveData<R>, block: ActivityActuator<R>.() -> Unit) {
        ActivityActuator(this as LifecycleOwner, liveData).apply(block)
    }
}