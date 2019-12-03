package com.zhan.mvvm.delegate

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.utils.ViewModelFactory

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
class MvmFragmentDelegateImpl(private val fm: FragmentManager, private val fragment: Fragment)
    : FragmentDelegateImpl(fm, fragment), IMvmFragment {

    override fun getLayoutId(): Int = 0

    private val iMvmFragment = fragment as IMvmFragment

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

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(v, savedInstanceState)
        iMvmFragment.dataObserver()
    }

    private fun initViewModel() {
        fragment.javaClass.fields
                .filter { it.isAnnotationPresent(BindViewModel::class.java) }
                .getOrNull(0)
                ?.apply {
                    isAccessible = true
                    set(fragment, ViewModelFactory.getFragmentViewModel(fragment, this, observer))
                }
    }

    override fun showError(msg: String) {
        fragment.toast(R.string.unkown_error)
        msg.showLog()
        hideLoading()
    }

    override fun showToast(msg: String) {
        fragment.toast(msg)
        hideLoading()
    }

    override fun showToast(@StringRes strRes: Int) {
        fragment.toast(strRes)
        hideLoading()
    }

    override fun showEmptyView() {}

    override fun showLoading() {}

    override fun hideLoading() {}
}