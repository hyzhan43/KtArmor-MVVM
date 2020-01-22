package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhan.mvvm.common.SharedDataFactory
import com.zhan.mvvm.common.ViewModelFactory
import com.zhan.mvvm.mvvm.BaseViewModel
import java.lang.reflect.Field

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   TODO
 */
class MvmActivityDelegateImpl(private val activity: Activity)
    : ActivityDelegateImpl(activity), IMvmActivity {

    private val iMvmActivity = activity as IMvmActivity

    private val observer: Observer<SharedData> by lazy { SharedDataFactory.initSharedData(iMvmActivity) }

    private val fragmentActivity by lazy { activity as FragmentActivity }

    private var viewModel: BaseViewModel<*>? = null

    override fun getLayoutId(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelFactory.injectViewModelByFragmentActivity(fragmentActivity)
        super.onCreate(savedInstanceState)

        observerSharedData()

        iMvmActivity.dataObserver()
    }

    private fun observerSharedData() {
        // 订阅通用 observer
        viewModel?.sharedData?.observe(fragmentActivity, observer)
    }
}