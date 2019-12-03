package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.utils.ViewModelFactory

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   TODO
 */
class MvmActivityImpl(private val activity: Activity)
    : ActivityDelegateImpl(activity), IMvmActivity {

    override fun getLayoutId(): Int = 0

    private val iMvmActivity = activity as IMvmActivity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        iMvmActivity.dataObserver()
    }

    private fun initViewModel() {
        activity.javaClass.fields
                .filter { it.isAnnotationPresent(BindViewModel::class.java) }
                .getOrNull(0)
                ?.apply {
                    isAccessible = true
                    set(activity, ViewModelFactory.getActivityViewModel(activity as FragmentActivity, this, observer))
                }
    }

    override fun showError(msg: String) {
        activity.toast(R.string.unkown_error)
        msg.showLog()
        hideLoading()
    }

    override fun showToast(msg: String) {
        activity.toast(msg)
        hideLoading()
    }

    override fun showToast(@StringRes strRes: Int) {
        activity.toast(strRes)
        hideLoading()
    }

    override fun showEmptyView() {}

    override fun showLoading() {}

    override fun hideLoading() {}
}