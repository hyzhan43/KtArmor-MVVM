package com.zhan.mvvm.delegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.collection.LruCache
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhan.mvvm.mvvm.IMvmFragment
import com.zhan.mvvm.base.IFragment
import com.zhan.mvvm.config.Setting


/**
 *  @author: HyJame
 *  @date:   2019-11-27
 *  @desc:   TODO
 */
object FragmentLifecycle : FragmentManager.FragmentLifecycleCallbacks() {

    private val cache by lazy { LruCache<String, FragmentDelegate>(Setting.FRAGMENT_CACHE_SIZE) }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {

        if (f !is IFragment) {
            return
        }

        val fragmentDelegate = fetchFragmentDelegate(f, fm)

        fragmentDelegate.onAttached(context)
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        fetchFragmentDelegateFromCache(f)?.onCreated(savedInstanceState)
    }

    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        fetchFragmentDelegateFromCache(f)?.onViewCreated(v, savedInstanceState)
    }

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        fetchFragmentDelegateFromCache(f)?.onActivityCreate(savedInstanceState)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onStarted()
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onResumed()
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onPaused()
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onStopped()
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        fetchFragmentDelegateFromCache(f)?.onSaveInstanceState(outState)
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onViewDestroyed()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onDestroyed()
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        fetchFragmentDelegateFromCache(f)?.onDetached()
    }

    private fun fetchFragmentDelegate(f: Fragment, fm: FragmentManager): FragmentDelegate {
        return fetchFragmentDelegateFromCache(f)
                ?: newDelegate(fm, f).apply { cache.put(getKey(f), this) }
    }

    private fun fetchFragmentDelegateFromCache(fragment: Fragment): FragmentDelegate? {
        if (fragment !is IFragment) {
            return null
        }

        return cache[getKey(fragment)]
    }

    private fun newDelegate(fm: FragmentManager, f: Fragment): FragmentDelegate {

        if (f is IMvmFragment) {
            return MvmFragmentDelegateImpl(fm, f)
        }

        return FragmentDelegateImpl(fm, f)
    }

    private fun getKey(f: Fragment) = f.javaClass.name + f.hashCode()
}
