package com.zhan.mvvm.mvvm.actuator

import com.zhan.mvvm.bean.KResponse
import com.zhan.mvvm.ext.launchUI
import kotlinx.coroutines.CoroutineScope

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    普通 DSL 请求处理器
 */
open class RequestActuator<R>(private val viewModelScope: CoroutineScope) {

    /**
     *  最开始执行 block
     */
    private var startBlock: (() -> Unit)? = null

    /**
     *  成功 block, 返回值是 KResponse<R>
     */
    var successRspBlock: ((KResponse<R>) -> Unit)? = null

    /**
     *  成功 block, 返回值是 (R)
     */
    var successBlock: ((R?) -> Unit) = { success(it) }

    /**
     *  失败 block
     */
    var failureBlock: ((String?) -> Unit) = { failure(it) }

    /**
     *  异常 block
     */
    var exceptionBlock: ((Throwable?) -> Unit) = { exception(it) }


    fun onStart(block: () -> Unit) {
        this.startBlock = block
    }

    fun request(block: suspend CoroutineScope.() -> KResponse<R>?) {

        startBlock?.invoke()

        viewModelScope.launchUI({

            block()?.apply {
                execute(successBlock, failureBlock)
                executeRsp(successRspBlock, failureBlock)
            }
        }, {
            exceptionBlock.invoke(it)
        })
    }

    fun onSuccessRsp(block: (KResponse<R>) -> Unit) {
        this.successRspBlock = block
    }

    fun onSuccess(block: (R?) -> Unit) {
        this.successBlock = block
    }

    fun onFailure(block: (String?) -> Unit) {
        this.failureBlock = block
    }

    fun onException(block: (Throwable?) -> Unit) {
        this.exceptionBlock = block
    }

    open fun success(data: R?) {

    }

    open fun failure(message: String?) {

    }

    open fun exception(throwable: Throwable?) {

    }
}