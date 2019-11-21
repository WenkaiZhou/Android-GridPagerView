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
package com.kevin.gridpager.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kevin.gridpager.GridViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var gridViewPager: GridViewPager
    private lateinit var indicatorView: IndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridViewPager = findViewById(R.id.cate_page)
        indicatorView = findViewById(R.id.indicator_view)
        gridViewPager.setGridViewAdapter(CategoryItemAdapter())

        indicatorView.setupViewPager(gridViewPager)
        initData(20)
    }

    private fun initData(count: Int) {
        val list: MutableList<CategoryBean> = mutableListOf()
        for (i in 0 until count) {
            val categoryLevel1Bean = CategoryBean("条目 $i")
            list.add(categoryLevel1Bean)
        }
        gridViewPager.setData(list)
        indicatorView.initIndicator(gridViewPager.adapter!!.count, gridViewPager.currentItem)
    }

    fun onColumnClick(view: View) {
        val columnView: TextView = findViewById(R.id.tv_column_number)
        SelectorDialog.instance
            .setTitle("设置列数")
            .setData((3..10).toMutableList())
            .setSelectedItem(columnView.text.toString().toInt())
            .show(this, object : SelectorDialog.OnSelectListener {
                override fun onSelect(data: Int) {
                    columnView.text = data.toString()
                    gridViewPager.setPageColumns(data)
                    indicatorView.initIndicator(gridViewPager.adapter!!.count, gridViewPager.currentItem)
                }

            })
    }

    fun onRowClick(view: View) {
        val rowView: TextView = findViewById(R.id.tv_row_number)
        SelectorDialog.instance
            .setTitle("设置行数")
            .setData((1..10).toMutableList())
            .setSelectedItem(rowView.text.toString().toInt())
            .show(this, object : SelectorDialog.OnSelectListener {
                override fun onSelect(data: Int) {
                    rowView.text = data.toString()
                    gridViewPager.setPageRows(data)
                    indicatorView.initIndicator(gridViewPager.adapter!!.count, gridViewPager.currentItem)
                }
            })
    }

    fun onTotalClick(view: View) {
        val totalView: TextView = findViewById(R.id.tv_total_number)
        SelectorDialog.instance
            .setTitle("设置数据总数")
            .setData((0 until 160 step 10).toMutableList())
            .setSelectedItem(totalView.text.toString().toInt())
            .show(this, object : SelectorDialog.OnSelectListener {
                override fun onSelect(data: Int) {
                    totalView.text = data.toString()
                    initData(data)
                }
            })
    }

}
