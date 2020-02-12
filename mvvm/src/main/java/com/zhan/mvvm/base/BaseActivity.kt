package com.zhan.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

@Deprecated("直接实现 IActivity 接口即可")
abstract class BaseActivity : AppCompatActivity(), IActivity, CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 在界面未初始化之前调用的初始化窗口
        initWidows()

        if (initArgs(intent.extras)) {
            setContentView(getLayoutId())
            super.onCreate(savedInstanceState)

            initBefore()
            initView()
            initListener()
            initData()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 取消协程
        if (isActive) {
            cancel()
        }
    }
}