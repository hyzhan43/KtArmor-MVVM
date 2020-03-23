package com.zhan.mvvm.mvvm.actuator

import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.mvvm.actuator.impl.DefaultLiveDataActuator
import com.zhan.mvvm.mvvm.livedata.CommonLiveData
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

    private val liveDataActuator by lazy { KtArmor.liveDataActuator ?: DefaultLiveDataActuator() }

    override fun success(data: R?) {
        liveDataActuator.success(liveData, data)
    }

    override fun failure(message: String?) {
        liveDataActuator.failure(liveData, message)
    }

    override fun exception(throwable: Throwable?) {
        liveDataActuator.exception(liveData, throwable)
    }
}