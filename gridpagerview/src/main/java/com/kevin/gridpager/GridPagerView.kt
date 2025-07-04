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

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * GridPagerView
 *
 * @author zwenkai@foxmail.com, Created on 2019-05-03 10:13:40
 * Major Function：<b>GridViewPager</b>
 *
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class GridPagerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private val adapter: GridPagerAdapter = GridPagerAdapter()

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.GridPagerView)
        val pageColumns = a.getInt(R.styleable.GridPagerView_gpv_pageColumns, DEFAULT_PAGE_COLUMN)
        val pageRows = a.getInt(R.styleable.GridPagerView_gpv_pageRows, DEFAULT_PAGE_ROW)
        a.recycle()
        adapter.setPageColumnsAndRows(pageColumns, pageRows)
        setAdapter(adapter)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return false
    }

    fun setPageColumns(columns: Int) {
        adapter.setPageColumns(columns)
    }

    fun setPageRows(rows: Int) {
        adapter.setPageRows(rows)
    }

    fun setDataItems(dataItems: MutableList<*>) {
        adapter.setDataItems(dataItems)
    }

    fun setGridViewAdapter(adapter: GridViewAdapter<*, *>) {
        this.adapter.setGridViewAdapter(adapter)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val childHeight = child.measuredHeight
            if (childHeight > maxHeight) {
                maxHeight = childHeight
            }
        }
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    companion object {
        private const val DEFAULT_PAGE_COLUMN = 4
        private const val DEFAULT_PAGE_ROW = 2
    }

}
