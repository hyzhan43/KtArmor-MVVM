package com.zhan.ktarmor.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 *  author: HyJame
 *  date:   2020-03-29
 *  desc:   TODO
 */
class TwoOneAdapter(
        fm: FragmentManager,
        val titles: List<String>,
        private val fragments: List<Fragment>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}