package com.zhan.mvvm.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhan.ktwing.ext.showLog
import com.zhan.ktwing.ext.tryCatch
import com.zhan.mvvm.bean.KResponse
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.config.Setting
import com.zhan.mvvm.common.Clazz
import com.zhan.mvvm.constant.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    ViewModel 基类, 封装了基本 请求方法, repository创建等
 */
abstract class BaseViewModel<T> : ViewModel(), IMvmView {

    val sharedData by lazy { MutableLiveData<SharedData>() }

    // 通过反射注入 repository
    val repository: T by lazy { Clazz.getClass<T>(this).newInstance() }

    fun launchUI(block: suspend CoroutineScope.() -> Unit, error: ((Throwable) -> Unit)? = null): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            tryCatch({
                block()
            }, {
                error?.invoke(it) ?: showException(it.toString())
            })
        }
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

    fun <R> quickLaunch(block: Execute<R>.() -> Unit) {
        Execute<R>().apply(block)
    }

    inner class Execute<R> {

        private var startBlock: (() -> Unit)? = null

        private var successBlock: ((R?) -> Unit)? = null
        private var successRspBlock: ((KResponse<R>) -> Unit)? = null

        private var failBlock: ((String?) -> Unit) = { showToast(it ?: Setting.MESSAGE_EMPTY) }
        private var exceptionBlock: ((Throwable?) -> Unit)? = null

        fun onStart(block: () -> Unit) {
            this.startBlock = block
        }

        fun request(block: suspend CoroutineScope.() -> KResponse<R>?) {

            startBlock?.invoke()

            launchUI({

                successBlock?.let {
                    block()?.execute(successBlock, failBlock)
                } ?: block()?.executeRsp(successRspBlock, failBlock)

            }, exceptionBlock)
        }

        fun onSuccess(block: (R?) -> Unit) {
            this.successBlock = block
        }

        fun onSuccessRsp(block: (KResponse<R>) -> Unit) {
            this.successRspBlock = block
        }

        fun onFail(block: (String?) -> Unit) {
            this.failBlock = block
        }

        fun onException(block: (Throwable?) -> Unit) {
            this.exceptionBlock = block
        }
    }
}