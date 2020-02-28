package com.zhan.ktarmor.account

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhan.ktarmor.R
import com.zhan.ktwing.ext.Toasts.toast
import com.zhan.ktwing.ext.str
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    TODO
 */
class LoginActivity : AppCompatActivity(), IMvmActivity {

    val mIdlingResource by lazy { LoginIdlingResource() }

    @BindViewModel
    lateinit var viewModel: AccountViewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initListener() {

        mBtnLogin.setOnClickListener {
            mIdlingResource.isNotIdleState()
            viewModel.login(mTieAccount.str(), mTiePassword.str())
        }

        mBtnCollect.setOnClickListener {
            viewModel.collect()
        }
    }

    override fun showToast(msg: String) {
        super.showToast(msg)
        mIdlingResource.isIdleState()
    }

    override fun dataObserver() {

        viewModel.loginData.observe(this, {
            toast("登录成功")
        }, {
            toast("错误")
        })


        viewModel.collectData.observe(this, Observer {
            toast("收藏成功")
            hideLoading()
        })
    }
}
