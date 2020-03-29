package com.zhan.ktarmor.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.zhan.ktarmor.R
import com.zhan.ktarmor.home.fragment.OneFragment
import com.zhan.ktarmor.home.fragment.ThreeFragment
import com.zhan.ktarmor.home.fragment.TwoFragment
import com.zhan.mvvm.mvvm.IMvmActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class HomeActivity : AppCompatActivity(), IMvmActivity {

    lateinit var mCurrentFragment: Fragment

    private val oneFragment by lazy { OneFragment() }
    private val twoFragment by lazy { TwoFragment() }
    private val threeFragment by lazy { ThreeFragment() }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initView() {
        super.initView()

        mCurrentFragment = oneFragment

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mFlContent, mCurrentFragment).commit()

        initNavigationBar()
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_home, R.string.one))
            addItem(BottomNavigationItem(R.mipmap.ic_home, R.string.two))
            addItem(BottomNavigationItem(R.mipmap.ic_home, R.string.three))

            // 设置底部 BottomBar 默认选中 home
            setFirstSelectedPosition(0)
            // 初始化
            initialise()

            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {
                }

                override fun onTabUnselected(position: Int) {
                }

                override fun onTabSelected(position: Int) = switchFragment(position)
            })
        }
    }

    private fun switchFragment(position: Int) {
        when (position) {
            0 -> goTo(oneFragment)
            1 -> goTo(twoFragment)
            2 -> goTo(threeFragment)
        }
    }

    //复用 fragment
    private fun goTo(to: Fragment) {
        if (mCurrentFragment == to) {
            return
        }

        with(supportFragmentManager.beginTransaction()) {
            hide(mCurrentFragment)

            if (to.isAdded) {
                show(to)
            } else {
                add(R.id.mFlContent, to)
            }
            commit()
            mCurrentFragment = to
        }
    }
}