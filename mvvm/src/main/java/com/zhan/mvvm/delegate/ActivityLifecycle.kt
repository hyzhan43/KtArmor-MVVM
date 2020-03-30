package com.zhan.mvvm.delegate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.collection.LruCache
import androidx.fragment.app.FragmentActivity
import com.zhan.mvvm.common.ActivityManager
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.config.Setting

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
object ActivityLifecycle : Application.ActivityLifecycleCallbacks {

    private val cache by lazy { LruCache<String, ActivityDelegate>(Setting.ACTIVITY_CACHE_SIZE) }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let { ActivityManager.add(it) }

        if (activity !is IActivity) {
            return
        }

        val activityDelegate = fetchActivityDelegate(activity)
                ?: newDelegate(activity).apply { cache.put(getKey(activity), this) }

        activityDelegate.onCreate(savedInstanceState)

        registerFragmentCallback(activity)
    }


    private fun registerFragmentCallback(activity: Activity?) {

        if (activity !is FragmentActivity) return

        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycle, true)
    }

    override fun onActivityStarted(activity: Activity?) {
        fetchActivityDelegate(activity)?.onStart()
    }

    override fun onActivityResumed(activity: Activity?) {
        fetchActivityDelegate(activity)?.onResume()
    }

    override fun onActivityPaused(activity: Activity?) {
        fetchActivityDelegate(activity)?.onPause()
    }

    override fun onActivityStopped(activity: Activity?) {
        fetchActivityDelegate(activity)?.onStop()
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.let { ActivityManager.remove(it) }

        fetchActivityDelegate(activity)?.onDestroy()
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        fetchActivityDelegate(activity)?.onSaveInstanceState(activity, outState)
    }

    private fun fetchActivityDelegate(activity: Activity?): ActivityDelegate? {

        if (activity !is IActivity) {
            return null
        }

        return cache.get(getKey(activity))
    }

    private fun newDelegate(activity: Activity): ActivityDelegate {
        if (activity is IMvmActivity) {
            return MvmActivityDelegateImpl(activity)
        }

        return ActivityDelegateImpl(activity)
    }

    private fun getKey(activity: Activity): String = activity.javaClass.name + activity.hashCode()
}