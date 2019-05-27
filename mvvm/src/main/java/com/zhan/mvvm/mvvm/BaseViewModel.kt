package com.zhan.mvvm.mvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhan.mvvm.common.SharedData

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class BaseViewModel<T : BaseRepository>(application: Application) :
        AndroidViewModel(application) {

    val sharedData by lazy { MutableLiveData<SharedData>() }

    val repository by lazy { bindRepository() }

    abstract fun bindRepository(): T

    override fun onCleared() {
        super.onCleared()

        // TODO clear repository
    }
}