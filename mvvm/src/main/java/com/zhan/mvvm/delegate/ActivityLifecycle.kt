package com.zhan.mvvm.delegate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.LruCache
import androidx.fragment.app.FragmentActivity
import com.zhan.mvvm.common.ActivityManager
import com.zhan.mvvm.mvvm.IMvmActivity
import com.zhan.mvvm.base.IActivity





/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
object ActivityLifecycle : Application.ActivityLifecycleCallbacks {

    private val cacheActivityDelegate by lazy { LruCache<String, ActivityDelegate>(100) }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let { ActivityManager.add(it) }

        //配置ActivityDelegate
        if (activity is IActivity) {
            var activityDelegate = fetchActivityDelegate(activity)
            if (activityDelegate == null) {
                activityDelegate = realNewDelegate(activity)
                cacheActivityDelegate.put(activity.javaClass.name, activityDelegate)
            }
            activityDelegate.onCreate(savedInstanceState)
        }

        registerFragmentCallback(activity)
    }

    private fun fetchActivityDelegate(activity: Activity?): ActivityDelegate? {
        var activityDelegate: ActivityDelegate? = null
        if (activity is IActivity) {
            activityDelegate = cacheActivityDelegate.get(activity.javaClass.name)
        }
        return activityDelegate
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

    private fun realNewDelegate(activity: Activity): ActivityDelegate {
        if (activity is IMvmActivity) {
            return MvmActivityDelegateImpl(activity)
        }

        return ActivityDelegateImpl(activity)
    }
}