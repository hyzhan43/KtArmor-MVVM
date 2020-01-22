package com.zhan.mvvm.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.BaseViewModel
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2020-01-22
 *  desc:   TODO
 */
object ViewModelFactory {

    fun injectViewModelByFragment(fragment: Fragment): BaseViewModel<*>? {
        val field = fragment.javaClass.fields
                .filter { it.isAnnotationPresent(BindViewModel::class.java) }
                .getOrNull(0)


        return field?.run {
            isAccessible = true
            val viewModel: BaseViewModel<*> = ViewModelProviders.of(fragment).get(getFiledClazz(field))
            set(fragment, viewModel)

            viewModel
        }
    }

    fun injectViewModelByFragmentActivity(activity: FragmentActivity): BaseViewModel<*>? {
        val field = activity.javaClass.fields
                .filter { it.isAnnotationPresent(BindViewModel::class.java) }
                .getOrNull(0)


        return field?.run {
            isAccessible = true
            val viewModel: BaseViewModel<*> = ViewModelProviders.of(activity).get(getFiledClazz(field))
            set(activity, viewModel)

            viewModel
        }
    }


    @Suppress("UNCHECKED_CAST")
    private fun <T> getFiledClazz(field: Field) = field.genericType as Class<T>
}