package com.zhan.ktarmor.welcome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WelcomePagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}
