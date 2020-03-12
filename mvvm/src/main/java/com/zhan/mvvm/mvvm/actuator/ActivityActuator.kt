package com.zhan.mvvm.mvvm.actuator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zhan.mvvm.constant.Const
import com.zhan.mvvm.mvvm.IMvmView

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
class ActivityActuator<R>(
    private val owner: LifecycleOwner,
    private val liveData: CommonLiveData<R>
) {

    private val mvmActivity = owner as IMvmView

    /**
     *  失败 block
     */
    private var failureBlock: ((String?) -> Unit) = {
        mvmActivity.showToast(it ?: Const.UNKNOWN_ERROR)
    }

    /**
     *  异常 block, 默认实现 调用 showError, 提示 未知异常, 打印 Log
     */
    private var exceptionBlock: ((Throwable?) -> Unit) = {
        mvmActivity.showError(it?.message ?: "")
    }

    init {
        with(liveData) {
            errorLiveData.observe(owner, Observer {
                if (isException()) {
                    exceptionBlock(Throwable(exception))
                } else {
                    failureBlock(failureMessage)
                }
            })
        }
    }

    fun onSuccess(block: (R?) -> Unit) {
        liveData.observe(owner, Observer {
            block.invoke(it)
        })
    }

    fun onFailure(block: (String?) -> Unit) {
        this.failureBlock = block
    }

    fun onException(block: (Throwable?) -> Unit) {
        this.exceptionBlock = block
    }
}