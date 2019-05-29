package com.zhan.mvvm.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhan.mvvm.common.SharedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class BaseViewModel<T : BaseRepository> : ViewModel() {

    val sharedData by lazy { MutableLiveData<SharedData>() }

    val repository by lazy { bindRepository() }

    abstract fun bindRepository(): T

    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(Dispatchers.Main) {
        block()
    }
}