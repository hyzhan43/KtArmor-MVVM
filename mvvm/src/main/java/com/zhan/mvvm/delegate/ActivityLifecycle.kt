package com.zhan.mvvm.delegate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.mvvm.IMvmActivity

/**
 *  @author: HyJame
 *  @date:   2019-11-20
 *  @desc:   TODO
 */
object ActivityLifecycle : Application.ActivityLifecycleCallbacks {

    private val cacheActivityDelegate by lazy { HashMap<String, ActivityDelegate>() }

    private lateinit var activityDelegate: ActivityDelegate

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        forwardDelegateFunction(activity) { activityDelegate.onCreate(savedInstanceState) }

        registerFragmentCallback(activity)
    }

    private fun registerFragmentCallback(activity: Activity?) {

        if (activity !is FragmentActivity) return

        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycle, true)
    }

    override fun onActivityStarted(activity: Activity?) {
        forwardDelegateFunction(activity) { activityDelegate.onStart() }
    }

    override fun onActivityResumed(activity: Activity?) {
        forwardDelegateFunction(activity) { activityDelegate.onResume() }
    }

    override fun onActivityPaused(activity: Activity?) {
        forwardDelegateFunction(activity) { activityDelegate.onPause() }
    }

    override fun onActivityStopped(activity: Activity?) {
        forwardDelegateFunction(activity) { activityDelegate.onStop() }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        forwardDelegateFunction(activity) {
            activityDelegate.onDestroy()
            cacheActivityDelegate.clear()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        forwardDelegateFunction(activity) { activityDelegate.onSaveInstanceState(activity, outState) }
    }

    private fun forwardDelegateFunction(activity: Activity?, block: () -> Unit) {

        if (activity !is IActivity) return

        val key = activity.javaClass.name

        activityDelegate = cacheActivityDelegate[key] ?: newInstantDelegate(activity)
                .also { cacheActivityDelegate[key] = it }

        block()
    }

    private fun newInstantDelegate(activity: IActivity): ActivityDelegate {
        if (activity is IMvmActivity) {
            return MvmActivityDelegateImpl(activity)
        }

        return ActivityDelegateImpl(activity)
    }
}