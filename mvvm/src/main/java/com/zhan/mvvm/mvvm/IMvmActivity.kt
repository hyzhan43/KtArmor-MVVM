package com.zhan.mvvm.mvvm

import android.app.Activity
import androidx.annotation.StringRes
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.base.IActivity

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   TODO
 */
interface IMvmActivity: IActivity {

    fun dataObserver(){}

    fun showError(msg: String) {
        (this as Activity).toast(R.string.unkown_error)
        msg.showLog()
        hideLoading()
    }

    fun showToast(msg: String) {
        (this as Activity).toast(msg)
        hideLoading()
    }

    fun showToast(@StringRes strRes: Int){
        (this as Activity).toast(strRes)
        hideLoading()
    }

    fun showEmptyView() {}

    fun showLoading() {}

    fun hideLoading() {}
}
