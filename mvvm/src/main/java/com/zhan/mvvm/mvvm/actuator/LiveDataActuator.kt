package com.zhan.mvvm.mvvm.actuator

import kotlinx.coroutines.CoroutineScope

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
class LiveDataActuator<R>(
    viewModelScope: CoroutineScope,
    private val liveData: CommonLiveData<R>
) : RequestActuator<R>(viewModelScope) {

    override var successBlock: ((R?) -> Unit)?
        get() = { liveData.value = it }
        set(value) {}

    override var failureBlock: ((String?) -> Unit)?
        get() = { liveData.failureMessage = it }
        set(value) {}

    override var exceptionBlock: ((Throwable?) -> Unit)?
        get() = { liveData.exception = it }
        set(value) {}
}