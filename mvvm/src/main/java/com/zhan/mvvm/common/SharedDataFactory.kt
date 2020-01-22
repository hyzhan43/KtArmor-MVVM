package com.zhan.mvvm.common

import androidx.lifecycle.Observer
import com.zhan.mvvm.bean.SharedData
import com.zhan.mvvm.bean.SharedType
import com.zhan.mvvm.mvvm.IMvmView

/**
 *  author: HyJame
 *  date:   2019-12-03
 *  desc:   TODO
 */
object SharedDataFactory {

    fun initSharedData(view: IMvmView): Observer<SharedData> = Observer { sharedData ->
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