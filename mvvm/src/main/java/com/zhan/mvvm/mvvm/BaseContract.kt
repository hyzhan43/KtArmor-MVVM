package com.zhan.mvvm.mvvm

import androidx.annotation.StringRes

/**
 * author：  HyZhan
 * create：  2019/8/7
 * desc：    TODO
 */
interface BaseContract {

    fun showNetworkError(msg: String)

    fun showToast(msg: String)

    fun showToast(@StringRes strRes: Int)

    fun showEmptyView()

    fun showLoading()
}