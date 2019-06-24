package com.zhan.ktarmor.account

import androidx.lifecycle.Observer
import com.zhan.ktarmor.R
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.str
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
class LoginActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {

        mBtnLogin.setOnClickListener {
            viewModel.login(mEtAccount.str(), mEtPassword.str())
        }
    }

    override fun dataObserver() {
        viewModel.loginData.observe(this, Observer {
            toast(it?.errorMsg ?: "错误")
        })
    }
}