package com.zhan.ktarmor.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.mvvm.mvvm.IMvmFragment

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
class TestFragment: Fragment(), IMvmFragment {

    override fun getLayoutId(): Int = R.layout.fragment_test
}