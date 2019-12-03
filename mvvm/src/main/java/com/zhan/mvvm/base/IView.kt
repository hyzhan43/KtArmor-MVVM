package com.zhan.mvvm.base

import androidx.annotation.StringRes

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IView{

    fun showError(msg: String) {}

    fun showToast(msg: String) {}

    fun showToast(@StringRes strRes: Int) {}

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}
}