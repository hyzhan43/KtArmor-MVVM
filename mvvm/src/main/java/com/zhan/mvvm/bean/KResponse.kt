package com.zhan.mvvm.bean

/**
 * author：  HyZhan
 * create：  2019/8/1
 * desc：    TODO
 */
interface KResponse<T> {

    fun isSuccess(): Boolean

    fun getKMessage(): String

    fun getKData(): T
}