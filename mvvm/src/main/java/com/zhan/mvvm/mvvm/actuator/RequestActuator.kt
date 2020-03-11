package com.zhan.mvvm.mvvm.actuator

import com.zhan.ktwing.ext.tryCatch
import com.zhan.mvvm.bean.KResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
class RequestActuator<R>(private val viewModelScope: CoroutineScope) {

    private var startBlock: (() -> Unit)? = null

    private var successBlock: ((R?) -> Unit)? = null
    private var successRspBlock: ((KResponse<R>) -> Unit)? = null

    private var failureBlock: ((String?) -> Unit)? = null
    private var exceptionBlock: ((Throwable?) -> Unit)? = null

    fun onStart(block: () -> Unit) {
        this.startBlock = block
    }

    fun request(block: suspend CoroutineScope.() -> KResponse<R>?) {

        startBlock?.invoke()

        viewModelScope.launch(Dispatchers.Main) {
            tryCatch({
                block()?.execute(successBlock, failureBlock)
                block()?.executeRsp(successRspBlock, failureBlock)
            }, {
                exceptionBlock?.invoke(it)
            })
        }
    }

    fun onSuccess(block: (R?) -> Unit) {
        this.successBlock = block
    }

    fun onSuccessRsp(block: (KResponse<R>) -> Unit) {
        this.successRspBlock = block
    }

    fun onFailure(block: (String?) -> Unit) {
        this.failureBlock = block
    }

    fun onException(block: (Throwable?) -> Unit) {
        this.exceptionBlock = block
    }
}