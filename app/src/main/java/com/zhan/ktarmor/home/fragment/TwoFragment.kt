package com.zhan.ktarmor.home.fragment

import androidx.fragment.app.Fragment
import com.zhan.ktarmor.R
import com.zhan.ktarmor.home.adapter.TwoOneAdapter
import com.zhan.mvvm.base.IFragment
import kotlinx.android.synthetic.main.fragment_two.*

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class TwoFragment : Fragment(), IFragment {

    override fun getLayoutId(): Int = R.layout.fragment_two

    override fun initView() {
        super.initView()

        // 关联 viewPage 和 tabLayout
        mTlWxName.setupWithViewPager(mVpContent)
    }

    override fun initData() {
        super.initData()

        val titles = arrayListOf<String>()
        titles.add(getString(R.string.two_one))
        titles.add(getString(R.string.two_one))

        val fragments = arrayListOf<Fragment>()
        fragments.add(TwoOneFragment())
        fragments.add(TwoTwoFragment())

        mVpContent.adapter = TwoOneAdapter(childFragmentManager, titles, fragments)
    }
}