package com.zhan.mvvm.mvvm

import android.app.Activity
import androidx.annotation.StringRes
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.R

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IMvmView {

    fun showError(msg: String) {
        (this as Activity).toast(R.string.unkown_error)
        logd(msg)
        hideLoading()
    }

    fun showToast(msg: String) {
        (this as Activity).toast(msg)
        hideLoading()
    }

    fun showToast(@StringRes strRes: Int) {
        (this as Activity).toast(strRes)
        hideLoading()
    }

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}
}