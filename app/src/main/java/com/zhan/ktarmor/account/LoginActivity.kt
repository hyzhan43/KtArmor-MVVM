package com.zhan.ktarmor.account

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhan.ktarmor.R
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.str
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.delegate.IMvmActivity
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
class LoginActivity :AppCompatActivity(), IMvmActivity {

    @BindViewModel
    lateinit var viewModel: AccountViewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {

        mBtnLogin.setOnClickListener {
            viewModel.login(mEtAccount.str(), mEtPassword.str())
        }

        mBtnCollect.setOnClickListener {
            viewModel.collect()
        }
    }

    override fun dataObserver() {
        viewModel.loginData.observe(this, Observer {
            toast("登录成功")
            hideLoading()
        })

        viewModel.collectData.observe(this, Observer {
            toast("收藏成功")
            hideLoading()
        })
    }
}