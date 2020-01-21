package com.zhan.mvvm.delegate

import android.app.Activity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.showLog
import com.zhan.mvvm.R
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhan.mvvm.common.ViewModelFactory
import java.lang.reflect.Field

/**
 *  @author: HyJame
 *  @date:   2019-11-21
 *  @desc:   TODO
 */
class MvmActivityDelegateImpl(private val activity: Activity)
    : ActivityDelegateImpl(activity), IMvmActivity {

    override fun getLayoutId(): Int = 0

    private val iMvmActivity = activity as IMvmActivity

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
                    set(activity, getViewModel(this))
                }
    }

    private fun getViewModel(field: Field): ViewModel {
        return ViewModelFactory.getActivityViewModel(iMvmActivity, activity, field)
    }
}