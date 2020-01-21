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

    @BindViewModel
    lateinit var viewModel: AccountViewModel

    val mIdlingResource by lazy { LoginIdlingResource() }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {

        mBtnLogin.setOnClickListener {
            //耗时操作开始，设置空闲状态为false，阻塞测试线程
            mIdlingResource.setIdleState(false)
            viewModel.login(mTieAccount.str(), mTiePassword.str())
        }

        mBtnCollect.setOnClickListener {
            viewModel.collect()
        }
    }


    override fun postShowToast(msg: String) {
        super.postShowToast(msg)
        mIdlingResource.setIdleState(true)
    }

    override fun showToast(msg: String) {
        super.showToast(msg)
        toast(msg)
        mIdlingResource.setIdleState(true)
    }

    override fun showToast(strRes: Int) {
        super.showToast(strRes)

    }



    override fun dataObserver() {
        viewModel.loginData.observe(this, Observer {
            toast("登录成功")
            hideLoading()
            mIdlingResource.setIdleState(true)
        })

        viewModel.collectData.observe(this, Observer {
            toast("收藏成功")
            hideLoading()
        })
    }
}