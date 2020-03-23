package com.zhan.ktarmor.common.actuator

import com.zhan.mvvm.mvvm.actuator.CommonLiveData
import com.zhan.mvvm.mvvm.actuator.LiveDataActuator
import kotlinx.coroutines.CoroutineScope

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   TODO
 */
class MyLiveDataActuator<R>(viewModelScope: CoroutineScope, liveData: CommonLiveData<R>)
    : LiveDataActuator<R>(viewModelScope, liveData) {


}