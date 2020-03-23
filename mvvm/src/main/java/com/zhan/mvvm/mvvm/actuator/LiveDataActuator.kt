package com.zhan.mvvm.mvvm.actuator

import kotlinx.coroutines.CoroutineScope

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
open class LiveDataActuator<R>(
    viewModelScope: CoroutineScope,
    private val liveData: CommonLiveData<R>
) : RequestActuator<R>(viewModelScope) {

    override fun success(data: R?) {
        liveData.value = data
    }

    override fun failure(message: String?) {
        liveData.failureMessage = message
    }

    override fun exception(throwable: Throwable?) {
        liveData.exception = throwable
    }
}