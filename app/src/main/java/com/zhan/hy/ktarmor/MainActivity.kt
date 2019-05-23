package com.zhan.hy.ktarmor

import com.zhan.hy.ktarmor.account.LoginActivity
import com.zhan.hy.ktarmor.empty.EmptyActivity
import com.zhan.hy.mvvm.base.BaseActivity
import com.zhan.hy.mvvm.ext.startActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()

        btnEmpty.setOnClickListener {
            startActivity<EmptyActivity>()
        }

        btnLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }
}
