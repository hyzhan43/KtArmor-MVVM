package com.zhan.mvvm.mvvm.actuator.impl

import com.zhan.mvvm.constant.Const
import com.zhan.mvvm.mvvm.IMvmView
import com.zhan.mvvm.mvvm.actuator.IActivityActuator

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    quickObserveSuccess 默认 Activity actuator 实现
 */
open class DefaultActivityActuator : IActivityActuator {

    /**
     *  quickObserveSuccess -> onSuccess默认实现:  空
     */
    override fun <R> success(mvmView: IMvmView, data: R?) {}

    /**
     * quickObserveSuccess -> onFailure默认实现:  toast message (viewModel 传递过来的 message)
     */
    override fun failure(mvmView: IMvmView, message: String?) {
        mvmView.showToast(message ?: Const.UNKNOWN_ERROR)
    }

    /**
     * quickObserveSuccess -> onException默认实现: toast "未知异常"(固定), 打印 log
     */
    override fun exception(mvmView: IMvmView, throwable: Throwable?) {
        mvmView.showError(throwable?.message ?: "")
    }
}