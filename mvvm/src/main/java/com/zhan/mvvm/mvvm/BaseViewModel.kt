package com.zhan.mvvm.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhan.mvvm.common.SharedData
import com.zhan.mvvm.ext.showLog
import com.zhan.mvvm.ext.tryCatch
import com.zhan.mvvm.utils.Clzz
import kotlinx.coroutines.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class BaseViewModel<T : BaseRepository> : ViewModel() {

    val sharedData by lazy { MutableLiveData<SharedData>() }

    // 通过反射注入 repository
    val repository: T by lazy { Clzz.getClass<T>(this).newInstance() }

    fun launchUI(block: suspend CoroutineScope.() -> Unit, error: ((Throwable) -> Unit)? = null): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            tryCatch({
                block()
            }, {
                it.toString().showLog()
                error?.invoke(it)
            })
        }
    }
}