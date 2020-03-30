package com.zhan.ktarmor.home.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.mvvm.base.IFragment
import kotlinx.android.synthetic.main.fragment_two_one.*

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class TwoOneFragment: Fragment(), IFragment {

    override fun getLayoutId(): Int = R.layout.fragment_two_one

    override fun initView() {
        super.initView()

        mTvTitle.text = "Hello Two One"
    }

}