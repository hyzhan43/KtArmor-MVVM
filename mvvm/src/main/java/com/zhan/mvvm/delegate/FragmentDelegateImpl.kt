package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.base.IFragment

/**
 *  author: HyJame
 *  date:   2019-11-27
 *  desc:   TODO
 */
class FragmentDelegateImpl(private val fragmentManager: FragmentManager,
                           private val fragment: Fragment) : FragmentDelegate {

    private val iFragment = fragment as IFragment

    override fun onAttached(context: Context) {
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {

        iFragment.initBefore()
        iFragment.initView()
        iFragment.initListener()
        iFragment.initData()
    }

    override fun onActivityCreate(savedInstanceState: Bundle?) {
    }

    override fun onStarted() {
    }

    override fun onResumed() {
    }

    override fun onPaused() {
    }

    override fun onStopped() {
    }

    override fun onViewDestroyed() {
    }

    override fun onDestroyed() {
    }

    override fun onDetached() {
    }
}