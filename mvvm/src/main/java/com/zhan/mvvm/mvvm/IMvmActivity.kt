package com.zhan.mvvm.mvvm

import androidx.appcompat.app.AppCompatActivity
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.mvvm.actuator.ActivityActuator
import com.zhan.mvvm.mvvm.livedata.CommonLiveData

/**
 * author：  HyZhan
 * create：  2019/8/7
 * desc：    TODO
 */
interface IMvmActivity : IActivity, IMvmView {

    fun dataObserver() {}

    fun <R> quickObserve(liveData: CommonLiveData<R>, block: ActivityActuator<R>.() -> Unit) {
        ActivityActuator(this as AppCompatActivity, liveData).apply(block)
    }

    fun <R> quickObserveSuccess(liveData: CommonLiveData<R>, successBlock: (R?) -> Unit) {
        ActivityActuator(this as AppCompatActivity, liveData).onSuccess(successBlock)
    }

}