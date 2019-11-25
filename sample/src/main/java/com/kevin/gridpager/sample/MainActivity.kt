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
import com.kevin.gridpager.GridPagerView
import com.kevin.gridpager.sample.binding.BindingCategoryAdapter
import com.kevin.gridpager.sample.common.CommonCategoryAdapter
import com.kevin.gridpager.sample.entity.Category

class MainActivity : AppCompatActivity() {

    private lateinit var gridPagerView: GridPagerView
    private lateinit var indicatorView: IndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridPagerView = findViewById(R.id.cate_page)
        indicatorView = findViewById(R.id.indicator_view)
        // 通常的Adapter方式
        gridPagerView.setGridViewAdapter(CommonCategoryAdapter())
        // dataBinding的方式
//        gridPagerView.setGridViewAdapter(BindingCategoryAdapter())

        indicatorView.setupViewPager(gridPagerView)
        initData(20)
    }

    private fun initData(count: Int) {
        val list: MutableList<Category> = mutableListOf()
        for (i in 0 until count) {
            list.add(Category("item $i"))
        }
        gridPagerView.setDataItems(list)
        indicatorView.initIndicator(gridPagerView.adapter!!.count, gridPagerView.currentItem)
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
                    gridPagerView.setPageColumns(data)
                    indicatorView.initIndicator(gridPagerView.adapter!!.count, gridPagerView.currentItem)
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
                    gridPagerView.setPageRows(data)
                    indicatorView.initIndicator(gridPagerView.adapter!!.count, gridPagerView.currentItem)
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
