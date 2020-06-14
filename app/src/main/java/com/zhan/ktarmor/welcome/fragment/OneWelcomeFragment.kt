package com.zhan.ktarmor.welcome.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.vm.LoginViewModel
import com.zhan.ktarmor.welcome.WelcomeViewModel
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_one_welcome.*

class OneWelcomeFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: WelcomeViewModel

    override fun getLayoutId(): Int = R.layout.fragment_one_welcome

    override fun initView() {
        super.initView()

        mTvContent.text = "$viewModel"
    }
}
