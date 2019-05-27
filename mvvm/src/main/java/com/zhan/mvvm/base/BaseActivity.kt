package com.zhan.mvvm.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        initView()
        initData()
        setListener()
    }

    abstract fun getLayoutId(): Int

    open fun initView() {}

    open fun initData() {}

    open fun setListener() {}
}