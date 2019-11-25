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

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

/**
 * IndicatorView
 *
 * @author zwenkai@foxmail.com, Created on 2019-11-21 21:33:16
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class IndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var currentPosition = 0

    init {
        orientation = HORIZONTAL
    }

    fun setupViewPager(viewPager: ViewPager) {
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                setSelected(position)
                if (currentPosition != -1) {
                    setUnselected(currentPosition)
                }
                currentPosition = position
            }
        })
    }

    fun initIndicator(size: Int, index: Int = 0) {
        removeAllViews()
        for (i in 0 until size) {
            val dot = ImageView(context)
            dot.setBackgroundResource(R.drawable.selector_guide_dots)
            val dotWidth = LayoutParams.WRAP_CONTENT
            val dotHeight = LayoutParams.WRAP_CONTENT
            val dotParams = LayoutParams(dotWidth, dotHeight)
            dotParams.setMargins(4, 0, 4, 0)
            dot.isEnabled = i == index
            addView(dot, dotParams)
        }
    }

    fun setSelected(position: Int) {
        getChildAt(position).isEnabled = true
        getChildAt(position).requestLayout()
    }

    fun setUnselected(position: Int) {
        getChildAt(position).isEnabled = false
        getChildAt(position).requestLayout()
    }

}