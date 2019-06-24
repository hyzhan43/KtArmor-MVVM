package com.zhan.mvvm.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhan.mvvm.R
import com.zhan.mvvm.mvvm.BaseViewModel
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_list.*

/**
 * author：  HyZhan
 * create：  2019/6/24
 * desc：    TODO
 */
abstract class ListActivity<VM : BaseViewModel<*>, T, A : BaseQuickAdapter<T, *>>
    : LifecycleActivity<VM>() {

    // 当前页
    var page = 0
    /**
     * 总数据
     * 一定要设置！！！不然无法显示数据
     * 一般在搜索结果返回后设置
     */
    var total = 0

    var refreshColor = R.color.blue_400

    val adapter by lazy { bindAdapter() }

    override fun getLayoutId(): Int = R.layout.activity_list

    override fun initView() {
        super.initView()

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 初始化 article
        initRecyclerView()
    }

    override fun initData() {
        super.initData()

        onRefreshData()
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

    abstract fun bindAdapter(): A
    /**
     *  下拉刷新
     */
    abstract fun onRefreshData()

    /**
     *  上拉加载更多
     */
    abstract fun onLoadMoreData()

    // 判断是否有更多数据
    fun hasMore() = adapter.data.size < this.total

    fun addData(newData: List<T>) {
        if (srlRefresh.isRefreshing) {
            srlRefresh.isRefreshing = false
            adapter.setNewData(newData)
            return
        }

        adapter.addData(newData)

        if (!hasMore()) {
            adapter.loadMoreEnd()
        } else {
            adapter.loadMoreComplete()
        }
    }

    fun setMoreData(newData: List<T>) {
        adapter.addData(newData)

        if (!hasMore()) {
            adapter.loadMoreEnd()
        } else {
            adapter.loadMoreComplete()
        }
    }

    fun setNewData(newData: List<T>) {
        if (srlRefresh.isRefreshing) {
            srlRefresh.isRefreshing = false
            adapter.run {
                setNewData(newData)
                loadMoreComplete()
            }
        }
    }
}