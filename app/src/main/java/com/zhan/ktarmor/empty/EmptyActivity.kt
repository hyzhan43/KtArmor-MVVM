package com.zhan.ktarmor.empty

import com.zhan.ktarmor.R
import com.zhan.mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_empty.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
class EmptyActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_empty

    override fun initView() {

        mEmptyView.emptyText = "我是空"
        mEmptyView.errorText = "我是 Error"
        mEmptyView.errorDrawable = R.drawable.ic_launcher_background

        btnLoading.setOnClickListener {
            mEmptyView.triggerLoading()
        }

        btnEmpty.setOnClickListener {
            mEmptyView.triggerEmpty()
        }

        btnError.setOnClickListener {
            mEmptyView.triggerNetError()
        }

        btnOk.setOnClickListener {
            mEmptyView.triggerOk()
        }
    }
}