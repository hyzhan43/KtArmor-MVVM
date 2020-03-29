package com.zhan.mvvm.mvvm

import android.app.Activity
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
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
        realToast(R.string.unkown_error)
        logd(msg)
        hideLoading()
    }

    fun showToast(@StringRes strRes: Int) {
        realToast(strRes)
        hideLoading()
    }

    fun showToast(msg: String) {
        realToast(msg)
        hideLoading()
    }

    fun realToast(msg: String) {
        if (this is Fragment) {
            this.toast(msg)
        } else if (this is Activity) {
            this.toast(msg)
        }
    }

    fun realToast(strRes: Int) {
        if (this is Fragment) {
            this.toast(strRes)
        } else if (this is Activity) {
            this.toast(strRes)
        }
    }

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}
}