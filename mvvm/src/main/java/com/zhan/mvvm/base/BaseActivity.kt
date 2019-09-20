package com.zhan.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 在界面未初始化之前调用的初始化窗口
        initWidows()

        if (initArgs(intent.extras)) {
            setContentView(getLayoutId())

            initBefore()
            initView()
            initListener()
            initData()
        } else {
            finish()
        }
    }

    open fun initWidows() {}

    open fun initArgs(extras: Bundle?): Boolean = true

    abstract fun getLayoutId(): Int

    open fun initBefore() {}

    open fun initView() {}

    open fun initData() {}

    open fun initListener() {}

    override fun onDestroy() {
        super.onDestroy()
        // 取消协程
        if (isActive){
            cancel()
        }
    }
}