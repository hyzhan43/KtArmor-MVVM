package com.zhan.mvvm.mvvm.actuator.impl

import com.zhan.mvvm.mvvm.livedata.CommonLiveData
import com.zhan.mvvm.mvvm.actuator.ILiveDataActuator

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   viewModel superLaunch 默认处理器
 */
open class DefaultLiveDataActuator : ILiveDataActuator {

    /**
     * superLaunch -> onSuccess默认实现:  直接传递给 Activity的 quickObserve-> onSuccess
     */
    override fun <R> success(liveData: CommonLiveData<R>, data: R?) {
        liveData.value = data
    }

    /**
     * superLaunch -> onFailure默认实现:  直接传递给 Activity的 quickObserve-> onFailure
     */
    override fun <R> failure(liveData: CommonLiveData<R>, message: String?) {
        liveData.failureMessage = message
    }

    /**
     * superLaunch -> onException默认实现:  直接传递给 Activity的 quickObserve-> onException
     */
    override fun <R> exception(liveData: CommonLiveData<R>, throwable: Throwable?) {
        liveData.exception = throwable
    }
}