package com.zhan.mvvm.mvvm

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.base.BaseFragment
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.common.Clazz
import com.zhan.mvvm.widget.LoadingDialog

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
abstract class MvmFragment<VM : BaseViewModel<*>> : BaseFragment(), IMvmFragment {

    val loadingView by lazy { LoadingDialog.create(childFragmentManager) }

    lateinit var viewModel: VM

    override fun initData() {

        viewModel = ViewModelProviders.of(this).get(Clazz.getClass(this))
        viewModel.sharedData.observe(this, observer)
        dataObserver()
    }

    override fun dataObserver() {}

    override fun showError(msg: String) {
        toast(R.string.unkown_error)
        msg.showLog()
        hideLoading()
    }

    override fun showToast(msg: String) {
        toast(msg)
        hideLoading()
    }

    override fun showToast(strRes: Int) {
        toast(strRes)
        hideLoading()
    }

    override fun showEmptyView() {}

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    // 分发状态
    private val observer by lazy {
        Observer<SharedData> { sharedData ->
            sharedData?.run {
                when (type) {
                    SharedType.TOAST -> showToast(msg)
                    SharedType.ERROR -> showError(msg)
                    SharedType.SHOW_LOADING -> showLoading()
                    SharedType.HIDE_LOADING -> hideLoading()
                    SharedType.RESOURCE -> showToast(strRes)
                    SharedType.EMPTY -> showEmptyView()
                }
            }
        }
    }
}