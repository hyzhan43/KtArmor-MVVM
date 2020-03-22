package com.zhan.mvvm.mvvm.actuator

import com.zhan.mvvm.bean.KResponse
import com.zhan.mvvm.ext.launchUI
import kotlinx.coroutines.CoroutineScope
import java.net.CacheResponse

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
open class RequestActuator<R>(private val viewModelScope: CoroutineScope) {

    /**
     *  最开始执行 block
     */
    private var startBlock: (() -> Unit)? = null

    /**
     *  成功 block, 返回值是 (R)
     */
    protected open var successBlock: ((R?) -> Unit)? = null

    /**
     *  成功 block, 返回值是 KResponse<R>
     */
    protected open var successRspBlock: ((KResponse<R>) -> Unit)? = null

    /**
     *  失败 block
     */
    protected open var failureBlock: ((String?) -> Unit)? = null

    /**
     *  异常 block
     */
    protected open var exceptionBlock: ((Throwable?) -> Unit)? = null

    fun onStart(block: () -> Unit) {
        this.startBlock = block
    }

    fun request(requestBlock: suspend CoroutineScope.() -> KResponse<R>?) {

        startBlock?.invoke()

        viewModelScope.launchUI({ requestSuccess(requestBlock()) }, { exceptionBlock?.invoke(it) })
    }

    private fun requestSuccess(response: KResponse<R>?) = response?.apply {
        successBlock?.let { execute(successBlock, failureBlock) }
                ?: executeRsp(successRspBlock, failureBlock)
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