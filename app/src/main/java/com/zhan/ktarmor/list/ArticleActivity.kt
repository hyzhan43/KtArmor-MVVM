package com.zhan.ktarmor.list

import com.zhan.mvvm.base.ListActivity

/**
 * author：  HyZhan
 * create：  2019/6/24
 * desc：    TODO
 */
class ArticleActivity : ListActivity<ArticleViewModel, String, ArticleAdapter>() {

    override fun bindAdapter() = ArticleAdapter()

    override fun onRefreshData() {
        val test = arrayListOf<String>()
        for (i in 1..10) {
            test.add("张三 = $i")
        }
        total = 13
        addData(test)
    }

    override fun onLoadMoreData() {
        val test = arrayListOf("张三2", "李四2", "王五2")
        addData(test)
    }
}