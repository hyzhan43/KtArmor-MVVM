package com.zhan.mvvm.mvvm.livedata

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.constant.Const

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    通用 LiveData (T + String)
 */
class CommonLiveData<T> : MutableLiveData<T>() {

    val errorLiveData = MutableLiveData<String>()

    var exception: Throwable? = null

    var failureMessage: String? = ""

    fun failureMessage(value: String?){
        this.failureMessage = value
        errorLiveData.value = value
    }

    fun postFailureMessage(value: String?) {
        this.failureMessage = value
        errorLiveData.postValue(value)
    }

    fun exception(throwable: Throwable) {
        this.exception = throwable
        errorLiveData.value = throwable.message
    }

    fun postException(throwable: Throwable) {
        this.exception = throwable
        errorLiveData.postValue(throwable.message)
    }

    fun isException(): Boolean {
        return exception?.message == errorLiveData.value || errorLiveData.value == Const.UNKNOWN_ERROR
    }
}