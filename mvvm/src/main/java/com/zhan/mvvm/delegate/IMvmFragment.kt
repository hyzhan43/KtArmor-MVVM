package com.zhan.mvvm.delegate

import androidx.annotation.StringRes
import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IMvmFragment: IFragment{

    fun dataObserver(){}

    fun showError(msg: String) {}

    fun showToast(msg: String) {}

    fun showToast(@StringRes strRes: Int){}

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}
}