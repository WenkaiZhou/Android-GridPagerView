/*
 * Copyright (c) 2019 Kevin zhou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kevin.gridpager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import kotlin.math.ceil

/**
 * GridPagerAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2019-05-03 10:22:21
 * Major Function：<b>GridPagerAdapter</b>
 *
 *
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
internal class GridPagerAdapter : PagerAdapter() {

    private var pageColumns: Int = 0
    private var pageRows: Int = 0
    private var pageSize: Int = 0

    private var dataList: MutableList<Any> = mutableListOf()
    private lateinit var gridAdapter: GridViewAdapter<Any, RecyclerView.ViewHolder>
    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    init {
        this.pageColumns = DEFAULT_PAGE_COLUMN
        this.pageRows = DEFAULT_PAGE_ROW
        resetPageSize()
    }

    fun setPageColumns(columns: Int) {
        this.pageColumns = columns
        resetPageSize()
        notifyDataSetChanged()
    }

    fun setPageRows(rows: Int) {
        this.pageRows = rows
        resetPageSize()
        notifyDataSetChanged()
    }

    private fun resetPageSize() {
        this.pageSize = pageColumns * pageRows
        this.recycledViewPool.setMaxRecycledViews(0, pageSize * 2)
    }

    fun setGridViewAdapter(adapter: GridViewAdapter<*, *>) {
        @Suppress("UNCHECKED_CAST")
        this.gridAdapter = adapter as GridViewAdapter<Any, RecyclerView.ViewHolder>
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return ceil(dataList.size.toDouble() / pageSize).toInt()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = container.context
        val view = RecyclerView(context)
        view.setRecycledViewPool(recycledViewPool)
        view.overScrollMode = View.OVER_SCROLL_NEVER
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, pageColumns)
        layoutManager.recycleChildrenOnDetach = true
        view.layoutManager = layoutManager
        val adapter = RecyclerViewAdapter(pageSize * position, gridAdapter)
        adapter.setData(getPageData(position))
        view.adapter = adapter
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    fun setData(dataList: List<*>) {
        this.dataList.clear()
        this.dataList.addAll(dataList.filterNotNull())
        notifyDataSetChanged()
    }

    private fun getPageData(index: Int): List<Any> {
        var realIndex = index
        if (realIndex < 0) {
            realIndex = 0
        }

        var fromIndex = realIndex * pageSize
        var toIndex = (realIndex + 1) * pageSize
        if (fromIndex < 0) {
            fromIndex = 0
        }

        if (toIndex < 0) {
            toIndex = 0
        }

        if (fromIndex > dataList.size) {
            fromIndex = dataList.size
        }

        if (toIndex > dataList.size) {
            toIndex = dataList.size
        }

        return dataList.subList(fromIndex, toIndex)
    }

    private inner class RecyclerViewAdapter internal constructor(
        private val positionOffset: Int,
        private val realAdapter: GridViewAdapter<Any, RecyclerView.ViewHolder>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var pageDataList: MutableList<Any> = mutableListOf()

        internal fun setData(dataList: List<Any>) {
            this.pageDataList.clear()
            this.pageDataList.addAll(dataList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return realAdapter.onCreateViewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            realAdapter.onBindViewHolder(
                holder,
                positionOffset + position,
                position,
                pageDataList[position]
            )
        }

        override fun getItemCount(): Int {
            return pageDataList.size
        }
    }

    companion object {
        private const val DEFAULT_PAGE_COLUMN = 4
        private const val DEFAULT_PAGE_ROW = 2
    }
}
