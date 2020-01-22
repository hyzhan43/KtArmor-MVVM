package com.zhan.mvvm.delegate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.common.SharedDataFactory
import com.zhan.mvvm.common.ViewModelFactory
import com.zhan.mvvm.mvvm.BaseViewModel
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
class MvmFragmentDelegateImpl(private val fm: FragmentManager, private val fragment: Fragment)
    : FragmentDelegateImpl(fm, fragment), IMvmFragment {

    private val iMvmFragment = fragment as IMvmFragment

    private val observer: Observer<SharedData> by lazy { SharedDataFactory.initSharedData(iMvmFragment) }

    private var viewModel: BaseViewModel<*>? = null

    override fun getLayoutId(): Int = 0

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelFactory.injectViewModelByFragment(fragment)
        super.onViewCreated(v, savedInstanceState)

        observerSharedData()
        iMvmFragment.dataObserver()
    }

    private fun observerSharedData() {
        // 订阅通用 observer
        viewModel?.sharedData?.observe(fragment, observer)
    }
}