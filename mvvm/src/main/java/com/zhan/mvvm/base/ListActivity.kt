package com.zhan.mvvm.base

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhan.mvvm.R
import com.zhan.mvvm.mvvm.BaseViewModel
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.k_activity_list.*

/**
 * author：  HyZhan
 * create：  2019/6/24
 * desc：    TODO
 */
abstract class ListActivity<VM : BaseViewModel<*>, T> : LifecycleActivity<VM>() {

    // 当前页
    var page = 0

    var refreshColor = R.color.blue_400

    val adapter by lazy { bindAdapter() }

    override fun getLayoutId(): Int = R.layout.k_activity_list

    override fun initView() {
        super.initView()

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 初始化 article
        initRecyclerView()
    }


    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        srlRefresh.setColorSchemeResources(refreshColor)
        srlRefresh.setOnRefreshListener {
            page = 0
            onRefreshData()
        }
    }

    private fun initRecyclerView() {

        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = adapter

        // 上拉加载更多
        adapter.setOnLoadMoreListener({
            ++page
            onLoadMoreData()
        }, rvContent)
    }

    abstract fun bindAdapter(): BaseQuickAdapter<T, *>
    /**
     *  下拉刷新
     */
    open fun onRefreshData() {}

    /**
     *  上拉加载更多
     */
    open fun onLoadMoreData() {}

    fun addData(newData: List<T>) {

        if (newData.isEmpty()) {
            adapter.loadMoreEnd()
            return
        }

        if (srlRefresh.isRefreshing) {
            srlRefresh.isRefreshing = false
            adapter.setNewData(newData)
            return
        }

        adapter.addData(newData)
        adapter.loadMoreComplete()
    }
}