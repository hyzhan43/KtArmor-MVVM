package com.zhan.mvvm.bean.livedata2

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

    fun onSuccess(block: (R?) -> Unit) {
        liveData.observe(owner, Observer {
            block.invoke(it)
        })
    }

    fun onFailure(block: (String?) -> Unit) {
        liveData.errorLiveData.observe(owner, Observer {
            block.invoke(it)
        })
    }

//    fun onException(block: (Throwable?) -> Unit) {
//        this.exceptionBlock = block
//    }
}