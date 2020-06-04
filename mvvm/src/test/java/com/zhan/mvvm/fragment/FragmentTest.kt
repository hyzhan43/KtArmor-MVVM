package com.zhan.mvvm.fragment

import androidx.fragment.app.Fragment
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Test

/**
 *  author: HyJame
 *  date:   2020-03-26
 *  desc:   TODO
 */
class FragmentTest {

    @Test
    fun test_get_home_fragment_layout_id() {
        var clz = TestFragment::class.java.superclass

        while (clz?.name != Fragment::class.java.name) {
            clz = clz?.superclass
        }

        val field = clz.getDeclaredField("mContentLayoutId")
        assertNotNull(field)
    }
}