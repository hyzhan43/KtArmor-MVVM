package com.zhan.mvvm.mvvm.actuator

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.constant.Const

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    通用 LiveData (R + String)
 */
class CommonLiveData<R> : MutableLiveData<R>() {

    val errorLiveData = MutableLiveData<String>()

    var exception: Throwable? = null
        set(value) {
            field = value
            errorLiveData.value = value?.message ?: Const.UNKNOWN_ERROR
        }

    var failureMessage: String? = ""
        set(value) {
            field = value
            errorLiveData.value = value
        }

    fun postFailureMessage(value: String?) {
        failureMessage = value
        errorLiveData.postValue(value)
    }

    fun postException(throwable: Throwable) {
        this.exception = throwable
        errorLiveData.postValue(throwable.message)
    }

    fun isException(): Boolean {
        return exception != null
    }
}