package com.kevin.gridpager.sample

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kevin.gridpager.GridViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var gridViewPager: GridViewPager
    private lateinit var dotsView: LinearLayout
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridViewPager = findViewById(R.id.cate_page)
        dotsView = findViewById(R.id.dots)
        gridViewPager.setGridViewAdapter(CategoryItemAdapter())

        initIndicator()
        initData(20)
    }

    private fun initData(count: Int) {
        val list: MutableList<CategoryBean> = mutableListOf()
        for (i in 0 until count) {
            val categoryLevel1Bean = CategoryBean("条目 $i")
            list.add(categoryLevel1Bean)
        }
        gridViewPager.setData(list)
        initDots(gridViewPager.adapter!!.count, gridViewPager.currentItem)
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
                    initDots(gridViewPager.adapter!!.count, gridViewPager.currentItem)
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
                    initDots(gridViewPager.adapter!!.count, gridViewPager.currentItem)
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

    /**
     * 初始化指示器
     */
    private fun initIndicator() { // 指示器选中
        gridViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                dotsView.getChildAt(position).isEnabled = true
                dotsView.getChildAt(position).requestLayout()
                if (currentPosition != -1) {
                    dotsView.getChildAt(currentPosition).isEnabled = false
                    dotsView.getChildAt(currentPosition).requestLayout()
                }
                currentPosition = position
            }
        })
    }

    private fun initDots(size: Int, index: Int) {
        val dotsView: LinearLayout = findViewById(R.id.dots)
        dotsView.removeAllViews()
        for (i in 0 until size) {
            val dot = ImageView(this)
            dot.setBackgroundResource(R.drawable.selector_guide_dots)
            val dotWidth = LinearLayout.LayoutParams.WRAP_CONTENT
            val dotHeight = LinearLayout.LayoutParams.WRAP_CONTENT
            val dotParams =
                LinearLayout.LayoutParams(dotWidth, dotHeight)
            dotParams.setMargins(4, 0, 4, 0)
            dot.isEnabled = i == index
            dotsView.addView(dot, dotParams)
        }
    }
}
