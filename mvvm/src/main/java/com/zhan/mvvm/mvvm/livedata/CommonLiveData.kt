package com.zhan.mvvm.mvvm.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zhan.mvvm.constant.Const

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    通用 LiveData (T + String)
 */
class CommonLiveData<T> : MutableLiveData<T>() {

    companion object {
        const val EXCEPTION_FLAG = "error"
        const val FAILURE_FLAG = "failure"
    }

    val errorLiveData = MutableLiveData<String>()

    var exception: Throwable? = null
        set(value) {
            field = value
            errorLiveData.value = buildExceptionValue(value)
        }

    var failureMessage: String? = ""
        set(value) {
            field = value
            errorLiveData.value = buildFailureValue(value)
        }

    fun postFailureMessage(value: String?) = errorLiveData.postValue(buildFailureValue(value))

    fun postException(throwable: Throwable?) = errorLiveData.postValue(buildExceptionValue(throwable))

    private fun buildFailureValue(value: String?) = "${FAILURE_FLAG}:$value"

    private fun buildExceptionValue(throwable: Throwable?) =
            "${EXCEPTION_FLAG}:${throwable?.message ?: Const.UNKNOWN_ERROR}"

    fun observerError(owner: LifecycleOwner, exception: ((Throwable?) -> Unit), failure: ((String?) -> Unit)) {
        errorLiveData.observe(owner, Observer { value ->
            val rawValue = value.split(":")[1]
            if (isException(value)) {
                exception(Throwable(rawValue))
            } else {
                failure(rawValue)
            }
        })
    }

    private fun isException(value: String): Boolean {
        val flag = value.split(":")[0]
        return flag == EXCEPTION_FLAG
    }
}
