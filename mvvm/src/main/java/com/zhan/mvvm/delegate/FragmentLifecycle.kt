package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.base.IFragment

/**
 *  @author: HyJame
 *  @date:   2019-11-27
 *  @desc:   TODO
 */
object FragmentLifecycle : FragmentManager.FragmentLifecycleCallbacks() {

    private val cacheDelegate by lazy { HashMap<String, FragmentDelegate>() }

    private lateinit var fragmentDelegate: FragmentDelegate

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onAttached(context) }
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onCreated(savedInstanceState) }
    }

    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onViewCreated(v, savedInstanceState) }
    }

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onActivityCreate(savedInstanceState) }
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onStarted() }
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onResumed() }
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onPaused() }
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onStopped() }
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onSaveInstanceState(outState) }
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) { fragmentDelegate.onViewDestroyed() }
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) {
            fragmentDelegate.onDestroyed()

        }
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        forwardDelegateFunction(fm, f) {
            fragmentDelegate.onDetached()
            cacheDelegate.clear()
        }
    }

    private fun forwardDelegateFunction(fm: FragmentManager, f: Fragment, block: () -> Unit) {

        if (f !is IFragment) return

        if (!this::fragmentDelegate.isInitialized || !fragmentDelegate.isAdd()) {

            val key = f.javaClass.name

            fragmentDelegate = cacheDelegate[key] ?: newInstance(fm, f, key)
        }

        block()
    }

    private fun newInstance(fm: FragmentManager, f: Fragment, key: String): FragmentDelegate {
        return FragmentDelegateImpl(fm, f).also { cacheDelegate[key] = it }
    }
}