package com.zhan.ktarmor.welcome.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.vm.LoginViewModel
import com.zhan.ktarmor.account.vm.RegisterViewModel
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_three_welcome.*

class ThreeWelcomeFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: RegisterViewModel

    override fun getLayoutId(): Int = R.layout.fragment_three_welcome

    override fun initView() {
        super.initView()

        mTvContent.text = "$viewModel"
    }
}
