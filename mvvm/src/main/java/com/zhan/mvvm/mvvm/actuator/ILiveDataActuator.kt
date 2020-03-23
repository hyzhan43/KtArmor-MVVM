package com.zhan.mvvm.mvvm.actuator

import com.zhan.mvvm.mvvm.livedata.CommonLiveData

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   TODO
 */
interface ILiveDataActuator {

    fun <R> success(liveData: CommonLiveData<R>, data: R?)

    fun <R> failure(liveData: CommonLiveData<R>, message: String?)

    fun <R> exception(liveData: CommonLiveData<R>, throwable: Throwable?)
}