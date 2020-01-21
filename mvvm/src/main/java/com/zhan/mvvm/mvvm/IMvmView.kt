package com.zhan.mvvm.mvvm

import android.app.Activity
import androidx.annotation.StringRes
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
interface IMvmView {

    /**
     *  setValue 方法更新 LiveData (必须主线程)
     */

    fun showError(msg: String) {
        (this as Activity).toast(R.string.unkown_error)
        msg.showLog()
        hideLoading()
    }

    fun showToast(@StringRes strRes: Int) {
        showToast((this as Activity).getString(strRes))
    }

    fun showToast(msg: String) {
        (this as Activity).toast(msg)
        hideLoading()
    }

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}

    /**
     *  postValue 方式更新 LiveData (非主线程)
     */

    fun postShowError(msg: String) {}

    fun postShowToast(msg: String) {}

    fun postShowToast(@StringRes strRes: Int) {}

    fun postShowEmptyView() {}

    fun postShowLoading() {}

    fun postHideLoading() {}
}