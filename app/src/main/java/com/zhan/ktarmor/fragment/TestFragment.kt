package com.zhan.ktarmor.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
class TestFragment: Fragment(), IFragment {

    override fun getLayoutId(): Int = R.layout.fragment_test
}