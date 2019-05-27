package com.zhan.mvvm.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.StringRes
import com.zhan.mvvm.base.BaseActivity
import com.zhan.mvvm.common.SharedData
import com.zhan.mvvm.common.SharedType
import com.zhan.mvvm.ext.Toasts.toast

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class LifecycleActivity<VM : BaseViewModel<*>> : BaseActivity() {

    protected val viewModel by lazy { ViewModelProviders.of(this).get(getViewModel()) }

    abstract fun getViewModel(): Class<VM>

    open fun dataObserver() {}

    open fun showSuccess() {}

    open fun showError(msg: String) {
        toast(msg)
    }

    open fun showLoading() {}

    open fun showTips(@StringRes strRes: Int) {
        toast(getString(strRes))
    }

    open fun showEmptyView() {}

    // 分发状态
    private val observer by lazy {
        Observer<SharedData> { sharedData ->
            sharedData?.run {
                when (type) {
                    SharedType.SUCCESS -> showSuccess()
                    SharedType.ERROR -> showError(msg)
                    SharedType.LOADING -> showLoading()
                    SharedType.TIPS -> showTips(strRes)
                    SharedType.EMPTY -> showEmptyView()
                }
            }
        }
    }
}