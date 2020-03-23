package com.zhan.mvvm.mvvm.actuator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zhan.mvvm.KtArmor
import com.zhan.mvvm.mvvm.IMvmView
import com.zhan.mvvm.mvvm.actuator.impl.DefaultActivityActuator
import com.zhan.mvvm.mvvm.livedata.CommonLiveData

/**
 *  author: HyJame
 *  date:   2020-03-22
 *  desc:   activity DSL 基类
 */
class ActivityActuator<R>(private val owner: LifecycleOwner,
                          private val liveData: CommonLiveData<R>) {

    private val mvmActivity = owner as IMvmView

    private val activityActuator by lazy { KtArmor.activityActuator ?: DefaultActivityActuator() }

    private var successBlock: ((R?) -> Unit) = { success(it) }

    /**
     *  失败 block
     */
    private var failureBlock: ((String?) -> Unit) = { failure(it) }

    /**
     *  异常 block,
     */
    private var exceptionBlock: ((Throwable?) -> Unit) = { exception(it) }

    init {
        with(liveData) {
            errorLiveData.observe(owner, Observer {
                if (isException()) {
                    exceptionBlock(Throwable(exception))
                } else {
                    failureBlock(failureMessage)
                }
            })

            this.observe(owner, Observer { successBlock(it) })
        }
    }

    fun onSuccess(block: (R?) -> Unit) {
        this.successBlock = block
    }

    fun onFailure(block: (String?) -> Unit) {
        this.failureBlock = block
    }

    fun onException(block: (Throwable?) -> Unit) {
        this.exceptionBlock = block
    }

    /**
     *  viewModel 成功回调 view
     */
    private fun success(data: R?) = activityActuator.success(mvmActivity, data)

    /**
     *  viewModel 失败回调 view, 默认 toast 显示 message
     */
    private fun failure(message: String?) = activityActuator.failure(mvmActivity, message)


    /**
     * viewModel 异常回调 view, 默认实现 调用 showError, 提示 未知异常, 打印 Log
     */
    private fun exception(throwable: Throwable?) = activityActuator.exception(mvmActivity, throwable)
}