package com.zhan.hy.ktarmor.account

import com.zhan.hy.ktarmor.R
import com.zhan.hy.mvvm.ext.str
import com.zhan.hy.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
class LoginActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): Class<AccountViewModel> = AccountViewModel::class.java

    override fun initView() {
        mBtnLogin.setOnClickListener {
            viewModel.login(mEtAccount.str(), mEtPassword.str())
        }
    }
}