package com.zhan.mvvm.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.mvvm.BaseViewModel
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
object ViewModelFactory {

    fun getFragmentViewModel(fragment: Fragment, field: Field, observer: Observer<SharedData>): BaseViewModel<*> {
        val viewModel: BaseViewModel<*> = ViewModelProviders.of(fragment)
                .get(getFiledClazz(field))

        // 订阅通用 observer
        viewModel.sharedData.observe(fragment, observer)

        return viewModel
    }

    fun getActivityViewModel(activity: FragmentActivity, field: Field, observer: Observer<SharedData>): BaseViewModel<*> {
        val viewModel: BaseViewModel<*> = ViewModelProviders.of(activity)
                .get(getFiledClazz(field))

        // 订阅通用 observer
        viewModel.sharedData.observe(activity, observer)

        return viewModel
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getFiledClazz(field: Field) = field.genericType as Class<T>
}