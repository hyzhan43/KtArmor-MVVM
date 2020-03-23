package com.zhan.ktarmor.common.actuator

import com.zhan.mvvm.mvvm.actuator.impl.DefaultLiveDataActuator
import com.zhan.mvvm.mvvm.livedata.CommonLiveData

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   TODO
 */
class MyLiveDataActuator : DefaultLiveDataActuator() {

    override fun <R> success(liveData: CommonLiveData<R>, data: R?) {
        super.success(liveData, data)
        // TODO
    }

    override fun <R> failure(liveData: CommonLiveData<R>, message: String?) {
        super.failure(liveData, message)
        // TODO
    }

    override fun <R> exception(liveData: CommonLiveData<R>, throwable: Throwable?) {
        super.exception(liveData, throwable)
    }
}