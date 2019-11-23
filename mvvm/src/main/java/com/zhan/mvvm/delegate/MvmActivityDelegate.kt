package com.zhan.mvvm.delegate

import androidx.annotation.StringRes

/**
 * author：  HyZhan
 * create：  2019/8/7
 * desc：    TODO
 */
interface MvmActivityDelegate {

    fun showError(msg: String)

    fun showToast(msg: String)

    fun showToast(@StringRes strRes: Int)

    fun showEmptyView()

    fun showLoading()

    fun hideLoading()
}