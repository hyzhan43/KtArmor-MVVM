package com.zhan.ktarmor.common.actuator

import com.zhan.mvvm.mvvm.IMvmView
import com.zhan.mvvm.mvvm.actuator.impl.DefaultActivityActuator

/**
 *  author: HyJame
 *  date:   2020-03-23
 *  desc:   TODO
 */
class MyActivityActuator : DefaultActivityActuator() {

    override fun <R> success(mvmView: IMvmView, data: R?) {
        super.success(mvmView, data)
        // TODO
    }

    override fun failure(mvmView: IMvmView, message: String?) {
        super.failure(mvmView, message)
        // TODO
    }

    override fun exception(mvmView: IMvmView, throwable: Throwable?) {
        super.exception(mvmView, throwable)
        // TODO
    }
}