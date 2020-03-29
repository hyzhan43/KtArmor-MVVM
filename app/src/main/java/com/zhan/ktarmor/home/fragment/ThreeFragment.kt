package com.zhan.ktarmor.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class ThreeFragment: Fragment(), IFragment {

    override fun getLayoutId(): Int = R.layout.fragment_three

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_two, container, false)
//    }
}