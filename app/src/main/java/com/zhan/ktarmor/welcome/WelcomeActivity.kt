package com.zhan.ktarmor.welcome

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.ktarmor.welcome.adapter.WelcomePagerAdapter
import com.zhan.ktarmor.welcome.fragment.OneWelcomeFragment
import com.zhan.ktarmor.welcome.fragment.ThreeWelcomeFragment
import com.zhan.ktarmor.welcome.fragment.TwoWelcomeFragment
import com.zhan.mvvm.mvvm.IMvmActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), IMvmActivity {

    override fun getLayoutId(): Int = R.layout.activity_welcome

    override fun initView() {
        super.initView()

        vpContent.adapter = WelcomePagerAdapter(this.supportFragmentManager, getFragments())

    }

    private fun getFragments(): List<Fragment> = arrayListOf(OneWelcomeFragment(), TwoWelcomeFragment(), ThreeWelcomeFragment())
}
