package com.zhan.hy.ktarmor

import com.zhan.hy.mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()

        btnClick.setOnClickListener {
            mEmptyView.triggerLoading()
        }
    }
}
