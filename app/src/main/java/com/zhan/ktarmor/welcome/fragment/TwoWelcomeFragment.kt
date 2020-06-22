package com.zhan.ktarmor.welcome.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.ktarmor.account.vm.LoginViewModel
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.annotation.BindViewModel
import com.zhan.mvvm.mvvm.IMvmFragment
import kotlinx.android.synthetic.main.fragment_two_welcome.*

class TwoWelcomeFragment : Fragment(), IMvmFragment {

    @BindViewModel
    lateinit var viewModel: LoginViewModel

    override fun getLayoutId(): Int = R.layout.fragment_two_welcome

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logd("[TwoWelcomeFragment] viewModel = $viewModel")
    }

    override fun initView() {
        super.initView()

        mTvContent.text = "$viewModel"
    }
}
