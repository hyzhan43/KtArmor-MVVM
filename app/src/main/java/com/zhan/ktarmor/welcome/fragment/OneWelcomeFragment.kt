package com.zhan.ktarmor.welcome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhan.ktarmor.R
import com.zhan.ktarmor.base.BaseFragment
import com.zhan.ktarmor.welcome.WelcomeViewModel
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.annotation.BindViewModel
import kotlinx.android.synthetic.main.fragment_one_welcome.*

class OneWelcomeFragment : BaseFragment() {

    @BindViewModel
    lateinit var viewModel: WelcomeViewModel

    override fun getLayoutId(): Int = R.layout.fragment_one_welcome

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logd("[OneWelcomeFragment] viewModel = $viewModel")
    }

    override fun initView() {
        super.initView()

        mTvContent.text = "$viewModel"
    }
}
