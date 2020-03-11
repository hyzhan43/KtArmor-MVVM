package com.zhan.mvvm.bean.livedata2

import androidx.lifecycle.MutableLiveData

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    通用 LiveData (R + String)
 */
class CommonLiveData<R> : MutableLiveData<R>() {

    val errorLiveData = MutableLiveData<String>()

    var exception: Throwable? = null

    var errorValue: String? = ""
        set(value) {
            field = value
            errorLiveData.value = errorValue
        }

    fun postErrorValue(value: String?) {
        errorLiveData.postValue(value)
    }
}