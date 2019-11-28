package com.zhan.ktarmor.fragment

import androidx.appcompat.app.AppCompatActivity
import com.zhan.ktarmor.R
import com.zhan.mvvm.base.IActivity

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
class TestActivity : AppCompatActivity(), IActivity {


    override fun getLayoutId(): Int = R.layout.activity_test

    override fun initView() {
        super.initView()

        supportFragmentManager.beginTransaction()
                .add(R.id.mContainer, TestFragment())
                .commit()
    }
}