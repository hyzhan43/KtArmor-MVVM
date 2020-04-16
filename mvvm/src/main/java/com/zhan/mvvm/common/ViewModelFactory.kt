package com.zhan.mvvm.common

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
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
 *  desc:   viewModel 创建工厂
 */
object ViewModelFactory {

    /**
     *  创建 对应的 ViewModel, 并且 添加 通用 SharedData (LiveData) 到 ViewModel中
     */
    fun createViewModel(field: Field, fragment: Fragment): BaseViewModel<*> {
        val viewModel = realCreateViewModel(field, fragment)
        initSharedData(fragment as IMvmFragment, viewModel)
        return viewModel
    }

    /**
     *  创建 对应的 ViewModel, 并且 添加 通用 SharedData (LiveData) 到 ViewModel中
     */
    fun createViewModel(field: Field, activity: Activity): BaseViewModel<*> {
        val viewModel = realCreateViewModel(field, activity as FragmentActivity)
        initSharedData(activity as IMvmActivity, viewModel)
        return viewModel
    }

    @Suppress("UNCHECKED_CAST")
    private fun realCreateViewModel(field: Field, owner: ViewModelStoreOwner): BaseViewModel<*> {
        val viewModelClass = field.genericType as Class<BaseViewModel<*>>

        return ViewModelProvider(owner).get(viewModelClass)
    }

    private fun initSharedData(view: IMvmView, viewModel: BaseViewModel<*>) {

        val observer: Observer<SharedData> = Observer { sharedData ->
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

        // 订阅通用 observer
        viewModel.sharedData.observe(view as LifecycleOwner, observer)
    }
}