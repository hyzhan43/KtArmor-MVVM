package com.zhan.ktarmor.home

import androidx.appcompat.app.AppCompatActivity
import com.zhan.ktarmor.R
import com.zhan.ktarmor.home.fragment.OneFragment
import com.zhan.ktwing.ext.logd
import com.zhan.mvvm.base.IActivity
import com.zhan.mvvm.mvvm.IMvmActivity

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class HomeActivity : AppCompatActivity(), IMvmActivity {

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initView() {
        super.initView()

        val oneFragment = OneFragment()
        val transaction = supportFragmentManager.beginTransaction()
        if (oneFragment.isAdded) {
            logd("isAdded1")
            transaction.show(oneFragment).commit()
            logd("isAdded2")
        } else {
            logd("is no Added1")
            transaction.add(R.id.mFlContent, oneFragment).commit()
            logd("is no Added2")
        }
    }
}