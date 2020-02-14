package com.zhan.mvvm.delegate

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.common.ViewModelFactory
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
class MvmFragmentDelegateImpl(private val fm: FragmentManager, private val fragment: Fragment)
    : FragmentDelegateImpl(fm, fragment), IMvmFragment {

    override fun getLayoutId(): Int = 0

    private val iMvmFragment = fragment as IMvmFragment

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
                    set(fragment, getViewModel(this))
                }
    }

    private fun getViewModel(field: Field) {
        ViewModelFactory.getFragmentViewModel(iMvmFragment, fragment, field)
    }
}