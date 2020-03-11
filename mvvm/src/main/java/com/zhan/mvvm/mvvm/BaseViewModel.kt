package com.zhan.mvvm.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.actuator.CommonLiveData
import com.zhan.mvvm.common.Clazz
import com.zhan.mvvm.config.Setting
import com.zhan.mvvm.ext.launchUI
import com.zhan.mvvm.mvvm.actuator.LiveDataActuator
import com.zhan.mvvm.mvvm.actuator.RequestActuator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    ViewModel 基类, 封装了基本 请求方法, repository创建等
 */
abstract class BaseViewModel<T> : ViewModel(), IMvmView {

    /**
     *  Activity级别, 公共 LiveData
     */
    val sharedData by lazy { MutableLiveData<SharedData>() }

    // 通过反射注入 repository
    val repository: T by lazy { Clazz.getClass<T>(this).newInstance() }

    fun launchUI(
        block: suspend CoroutineScope.() -> Unit,
        error: ((Throwable) -> Unit)? = null
    ): Job {
        return viewModelScope.launchUI({
            block()
        }, {
            error?.invoke(it) ?: showException(it.toString())
        })
    }

    private fun showException(exception: String) {
        exception.showLog()
        showError(Setting.UNKNOWN_ERROR)
    }

    override fun showToast(msg: String) {
        sharedData.value = SharedData(msg, type = SharedType.TOAST)
    }

    override fun showError(msg: String) {
        sharedData.value = SharedData(msg, type = SharedType.ERROR)
    }

    override fun showToast(@StringRes strRes: Int) {
        sharedData.value = SharedData(strRes = strRes, type = SharedType.RESOURCE)
    }

    override fun showEmptyView() {
        sharedData.value = SharedData(type = SharedType.EMPTY)
    }

    override fun showLoading() {
        sharedData.value = SharedData(type = SharedType.SHOW_LOADING)
    }

    override fun hideLoading() {
        sharedData.value = SharedData(type = SharedType.HIDE_LOADING)
    }

    fun <R> superLaunch(liveData: CommonLiveData<R>, block: LiveDataActuator<R>.() -> Unit) {
        LiveDataActuator(viewModelScope, liveData).apply(block)
    }

    fun <R> quickLaunch(block: RequestActuator<R>.() -> Unit) {
        RequestActuator<R>(viewModelScope).apply(block)
    }
}