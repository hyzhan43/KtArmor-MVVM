package com.zhan.hy.ktarmor.empty

import com.zhan.hy.ktarmor.R
import com.zhan.hy.mvvm.base.BaseActivity
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