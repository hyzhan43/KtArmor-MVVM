package com.zhan.mvvm.mvvm.actuator

import com.zhan.mvvm.mvvm.IMvmView

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   TODO
 */
interface IActivityActuator {

    fun <R> success(mvmView: IMvmView, data: R?)

    fun failure(mvmView: IMvmView, message: String?)

    fun exception(mvmView: IMvmView, throwable: Throwable?)
}