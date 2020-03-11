package com.zhan.mvvm.mvvm.actuator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
class ActivityActuator<R>(
    private val owner: LifecycleOwner,
    private val liveData: CommonLiveData<R>
) {

    /**
     *  失败 block
     */
    private var failureBlock: ((String?) -> Unit)? = null

    /**
     *  异常 block
     */
    private var exceptionBlock: ((Throwable?) -> Unit)? = null

    init {
        with(liveData) {
            errorLiveData.observe(owner, Observer {
                if (isException()) {
                    exceptionBlock?.invoke(Throwable(exception))
                } else {
                    failureBlock?.invoke(failureMessage)
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