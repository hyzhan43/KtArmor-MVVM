package com.zhan.mvvm.bean

import com.zhan.ktwing.ext.Toasts
import com.zhan.mvvm.config.Setting

/**
 * author：  HyZhan
 * create：  2019/8/1
 * desc：    封装了基本Response操作, 需配合 BaseViewModel使用
 */
interface KResponse<T> {

    fun isSuccess(): Boolean

    fun getKMessage(): String?

    fun getKData(): T?

    /**
     *  全局默认实现, 可根据自身业务 重写execute方法
     *  @param error            若有错误的回调, 默认getKMessage(), 否则返回 Setting.MESSAGE_EMPTY
     *  @param successResponse  成功的回调, 默认是返回 KResponse<T>
     */
    fun executeRsp(successResponse: ((KResponse<T>) -> Unit)?, error: ((String) -> Unit)? = null) {

        if (this.isSuccess()) {
            successResponse?.invoke(this)
            return
        }

        (this.getKMessage() ?: Setting.MESSAGE_EMPTY).let { error?.invoke(it) ?: Toasts.show(it) }
    }

    /**
     *  全局默认实现, 可根据自身业务 重写execute方法
     *  @param success          成功的回调, 默认是返回 getKData()
     *  @param error            若有错误的回调, 默认getKMessage(), 否则返回 Setting.MESSAGE_EMPTY
     */
    fun execute(success: ((T?) -> Unit)?, error: ((String) -> Unit)? = null) {

        if (this.isSuccess()) {
            success?.invoke(this.getKData())
            return
        }

        (this.getKMessage() ?: Setting.MESSAGE_EMPTY).let { error?.invoke(it) ?: Toasts.show(it) }
    }
}