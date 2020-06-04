package com.zhan.ktarmor.account.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.LoginIdlingResource
import com.zhan.ktarmor.account.vm.LoginViewModel
import com.zhan.ktarmor.account.vm.RegisterViewModel
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
    lateinit var loginViewModel: LoginViewModel

    @BindViewModel
    lateinit var registerViewModel: RegisterViewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initListener() {

        mBtnLogin.setOnClickListener {
            mIdlingResource.isNotIdleState()
            loginViewModel.login(mTieAccount.str(), mTiePassword.str())
        }

        mBtnConcurrentLogin.setOnClickListener {
            loginViewModel.loginByConcurrent()
        }

        mBtnLoginTest.setOnClickListener {
            loginViewModel.loginByTest(mTieAccount.str(), mTiePassword.str())
        }

        mBtnTestService.setOnClickListener {
            loginViewModel.loginByTestService(mTieAccount.str(), mTiePassword.str())
        }

        mBtnRegister.setOnClickListener {
            registerViewModel.register(mTieAccount.str(), mTiePassword.str())
        }
    }

    override fun showToast(msg: String) {
        super.showToast(msg)
        mIdlingResource.isIdleState()
    }

    override fun dataObserver() {

//        quickObserve(loginViewModel.loginData) {
//            onSuccess {
//                toast("登录成功")
//            }
//
//            onFailure {
//                toast("$it")
//            }
//
//            onException {
//                toast("${it?.message}")
//            }
//        }

        /**
         * 等同上面
         * onSuccess:  自定义实现
         * onFailure:  默认 toast message (viewModel 传递过来的 message)
         * onException: 默认 toast "未知异常"(固定), 打印 log
         */
        quickObserveSuccess(loginViewModel.loginData) {
            toast("登录成功")
        }

        quickObserve(loginViewModel.concurrentData){
            onFailure {
                mTvResult.append("\n $it")
            }
        }

        registerViewModel.registerData.observe(this, Observer {
            toast(it)
        })
    }
}
