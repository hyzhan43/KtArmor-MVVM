package com.zhan.mvvm.common

import android.app.Activity

/**
 *  author: HyJame
 *  date:   2020-02-10
 *  desc:   管理 所有 activity
 */
object ActivityManager {

    /**
     * 管理所有存活的 Activity
     */
    private val activityList by lazy { arrayListOf<Activity>() }

    fun add(activity: Activity) {
        synchronized(ActivityManager::class.java) {
            if (!activityList.contains(activity)) {
                activityList.add(activity)
            }
        }
    }

    /**
     * 删除集合里的指定的 Activity 实例
     */
    fun remove(activity: Activity) {
        synchronized(ActivityManager::class.java) {
            if (activityList.contains(activity)) {
                activityList.remove(activity)
            }
        }
    }

    /**
     * finish 所有Activity
     */
    fun finishAll() {
        synchronized(ActivityManager::class.java) {
            activityList.forEach { activity -> activity.finish() }
        }
    }
}
