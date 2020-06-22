package com.zhan.ktarmor.base

import androidx.fragment.app.Fragment
import com.zhan.mvvm.mvvm.IMvmFragment

/**
 *  author: HyJame
 *  date:   2020/6/22
 *  desc:   TODO
 */
open class BaseFragment : Fragment(), IMvmFragment {

    override fun getLayoutId(): Int = 0
}