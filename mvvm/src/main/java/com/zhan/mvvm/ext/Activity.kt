package com.zhan.mvvm.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.zhan.mvvm.utils.IntentUtils

/**
 * author：  HyZhan
 * create：  2019/8/1
 * desc：    TODO
 */
inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) IntentUtils.fillIntentArguments(intent, params)
    this.startActivity(intent)
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(context, T::class.java)
    if (params.isNotEmpty()) IntentUtils.fillIntentArguments(intent, params)
    activity?.startActivity(intent)
}