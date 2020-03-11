package com.zhan.mvvm.bean.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 *  author:  HyJame
 *  date:    2020/2/28
 *  desc:    测试中
 */
open class ComplexLiveData<T, E> {

    private val successLiveData by lazy { MutableLiveData<T>() }

    private val errorLiveData by lazy { MutableLiveData<E>() }

    open fun postSuccessValue(value: T?) {
        successLiveData.postValue(value)
    }

    open fun postErrorValue(value: E?) {
        errorLiveData.postValue(value)
    }

    open fun observe(
        owner: LifecycleOwner,
        successBlock: (T?) -> Unit,
        errorBlock: ((E?) -> Unit)? = null
    ) {
        this.successLiveData.observe(owner, Observer { successBlock(it) })

        this.errorLiveData.observe(owner, Observer { errorBlock?.invoke(it) })
    }
}
