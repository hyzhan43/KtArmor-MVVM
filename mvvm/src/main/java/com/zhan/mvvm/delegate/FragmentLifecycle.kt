package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.collection.LruCache
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.base.IFragment


/**
 *  @author: HyJame
 *  @date:   2019-11-27
 *  @desc:   TODO
 */
object FragmentLifecycle : FragmentManager.FragmentLifecycleCallbacks() {

    private val cacheDelegate by lazy { LruCache<String, FragmentDelegate>(100) }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {

        if (f is IFragment) {
            var fragmentDelegate = fetchFragmentDelegate(f)
            if (fragmentDelegate == null || !fragmentDelegate.isAdd()) {
                fragmentDelegate = realNewDelegate(fm, f)
                cacheDelegate.put(f.javaClass.name, fragmentDelegate)
            }
            fragmentDelegate.onAttached(context)
        }
    }


    private fun fetchFragmentDelegate(fragment: Fragment): FragmentDelegate? {
        if (fragment is IFragment) {
            return cacheDelegate[fragment.javaClass.name]
        }
        return null
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        fetchFragmentDelegate(f)?.onCreated(savedInstanceState)
    }

    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        fetchFragmentDelegate(f)?.onViewCreated(v, savedInstanceState)
    }

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        fetchFragmentDelegate(f)?.onActivityCreate(savedInstanceState)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onStarted()
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onResumed()
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onPaused()
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onStopped()
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        fetchFragmentDelegate(f)?.onSaveInstanceState(outState)
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onViewDestroyed()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onDestroyed()
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegate(f)?.onDetached()
    }

    private fun realNewDelegate(fm: FragmentManager, f: Fragment): FragmentDelegate {

        if (f is IMvmFragment) {
            return MvmFragmentDelegateImpl(fm, f)
        }

        return FragmentDelegateImpl(fm, f)
    }


}