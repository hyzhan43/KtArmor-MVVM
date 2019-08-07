package com.zhan.mvvm.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.R
import com.zhan.mvvm.base.ToolbarActivity
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.showLog
import com.zhan.mvvm.utils.Clzz

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class LifecycleActivity<VM : BaseViewModel<*>> : ToolbarActivity(), BaseContract {

    lateinit var viewModel: VM

    override fun initData() {
        super.initData()

        viewModel = ViewModelProviders.of(this).get(Clzz.getClass(this))
        viewModel.sharedData.observe(this, observer)
        dataObserver()
    }

    open fun dataObserver() {}

    override fun showError(msg: String) {
        toast(R.string.unkown_error)
        msg.showLog()
    }

    override fun showToast(msg: String) = toast(msg)

    override fun showToast(@StringRes strRes: Int) = toast(strRes)

    override fun showEmptyView() {}

    override fun showLoading() {}

    // 分发状态
    private val observer by lazy {
        Observer<SharedData> { sharedData ->
            sharedData?.run {
                when (type) {
                    SharedType.TOAST -> showToast(msg)
                    SharedType.ERROR -> showError(msg)
                    SharedType.LOADING -> showLoading()
                    SharedType.RESOURCE -> showToast(strRes)
                    SharedType.EMPTY -> showEmptyView()
                }
            }
        }
    }
}