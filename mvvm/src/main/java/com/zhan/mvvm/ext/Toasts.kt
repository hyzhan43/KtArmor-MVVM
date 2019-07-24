package com.zhan.mvvm.ext

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * @author  hyzhan
 * @date    2019/5/22
 * @desc    Toast 扩展函数
 */
object Toasts {

    private var mToast: Toast? = null

    @SuppressLint("ShowToast")
    fun init(context: Context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }

    fun show(message: String) {
        mToast?.apply { setText(message) }?.show()
    }


    /**
     *  防止重复 toast 显示
     *  如果 mToast不为空 就显示, 否则就创建新的 mToast
     */
    fun Context.toast(message: String) {
        mToast?.run {
            duration = Toast.LENGTH_SHORT
            setText(message)
            show()
        } ?: Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
            mToast = this
            show()
        }
    }

    fun Context.toast(@StringRes message: Int) {
        toast(message.toString())
    }

    fun Fragment.toast(message: String) {
        mToast?.run {
            duration = Toast.LENGTH_SHORT
            setText(message)
            show()
        } ?: Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
            mToast = this
            show()
        }
    }

    fun Fragment.toast(@StringRes strRes: Int) {
        toast(getString(strRes))
    }

    fun View.toast(message: String) {
        mToast?.run {
            duration = Toast.LENGTH_SHORT
            setText(message)
            show()
        } ?: Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
            mToast = this
            show()
        }
    }

    fun View.toast(@StringRes strRes: Int) {
        toast(context.getString(strRes))
    }
}