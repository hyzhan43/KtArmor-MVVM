package com.zhan.mvvm.common

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.BaseViewModel
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.mvvm.IMvmView
import java.lang.reflect.Field

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
object ViewModelFactory {

    private lateinit var observer: Observer<SharedData>

    private fun initSharedData(view: IMvmView) {
        observer = Observer { sharedData ->
            sharedData?.run {
                when (type) {
                    SharedType.TOAST -> view.showToast(msg)
                    SharedType.ERROR -> view.showError(msg)
                    SharedType.SHOW_LOADING -> view.showLoading()
                    SharedType.HIDE_LOADING -> view.hideLoading()
                    SharedType.RESOURCE -> view.showToast(strRes)
                    SharedType.EMPTY -> view.showEmptyView()
                }
            }
        }
    }

    /**
     * 创建 对应的 ViewModel, 并且 添加 通用 SharedData (LiveData) 到 ViewModel中
     */
    fun createViewModel(fragment: Fragment, field: Field): BaseViewModel<*> {
        val viewModel: BaseViewModel<*> = ViewModelProvider(fragment).get(getFiledClazz(field))

        initSharedData(fragment as IMvmFragment)

        // 订阅通用 observer
        viewModel.sharedData.observe(fragment, observer)

        return viewModel
    }

    /**
     *  创建 对应的 ViewModel, 并且 添加 通用 SharedData (LiveData) 到 ViewModel中
     */
    fun createViewModel(activity: Activity, field: Field): BaseViewModel<*> {

        val fragmentActivity = activity as FragmentActivity

        val viewModel: BaseViewModel<*> = ViewModelProvider(fragmentActivity).get(getFiledClazz(field))

        initSharedData(activity as IMvmActivity)

        // 订阅通用 observer
        viewModel.sharedData.observe(fragmentActivity, observer)

        return viewModel
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getFiledClazz(field: Field) = field.genericType as Class<T>
}