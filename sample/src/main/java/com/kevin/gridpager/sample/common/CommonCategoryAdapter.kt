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
package com.kevin.gridpager.sample.common

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kevin.gridpager.extras.ClickableGridViewAdapter
import com.kevin.gridpager.sample.R
import com.kevin.gridpager.sample.entity.Category
import java.util.Random

/**
 * CommonCategoryAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2019-11-21 19:03:08
 *         Major Function：<b>条目Adapter</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class CommonCategoryAdapter :
    ClickableGridViewAdapter<Category, CommonCategoryAdapter.ViewHolder>() {

    private val random = Random()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_common_category_item, parent, false)
        view.findViewById<ImageView>(R.id.iv_image).setBackgroundColor(getBackgroundColor())
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        pagePosition: Int,
        item: Category
    ) {
        super.onBindViewHolder(holder, position, pagePosition, item)
        holder.tvName.text = item.name
    }

    override fun onItemClick(view: View, item: Category, position: Int, pagePosition: Int) {
        Toast.makeText(
            view.context,
            "position = $position, pagePosition = $pagePosition, ${item.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getBackgroundColor(): Int {
        val hsv = floatArrayOf(random.nextInt(360).toFloat(), 0.71f, 0.85f)
        return Color.HSVToColor(hsv)
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
    }
}