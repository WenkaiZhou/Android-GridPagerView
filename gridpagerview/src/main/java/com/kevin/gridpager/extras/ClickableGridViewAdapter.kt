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
package com.kevin.gridpager.extras

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kevin.gridpager.GridViewAdapter
import com.kevin.gridpager.R

/**
 * ClickableGridViewAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2019-11-21 19:19:39
 *         Major Function：<b>Clickable GridViewAdapter</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
abstract class ClickableGridViewAdapter<T, VH : RecyclerView.ViewHolder> : GridViewAdapter<T, VH>(),
    View.OnClickListener, View.OnLongClickListener {

    override fun onBindViewHolder(holder: VH, position: Int, pagePosition: Int, item: T) {
        if (clickable(position, pagePosition) || longClickable(position, pagePosition)) {

            holder.itemView.setTag(R.id.tag_clickable_grid_view_adapter_holder, holder)
            holder.itemView.setTag(R.id.tag_clickable_grid_view_adapter_data, item)
            holder.itemView.setTag(R.id.tag_clickable_grid_view_adapter_position, position)

            if (clickable(position, pagePosition)) {
                holder.itemView.setOnClickListener(this)
            }

            if (clickable(position, pagePosition)) {
                holder.itemView.setOnLongClickListener(this)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onClick(view: View) {
        val holder = view.getTag(R.id.tag_clickable_grid_view_adapter_holder) as VH
        val item = view.getTag(R.id.tag_clickable_grid_view_adapter_data) as T
        val position = view.getTag(R.id.tag_clickable_grid_view_adapter_position) as Int
        val pagePosition = getPosition(holder)
        if (position == RecyclerView.NO_POSITION) {
            // ignore
            return
        }

        onItemClick(view, item, position, pagePosition)
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onLongClick(view: View): Boolean {
        val holder = view.getTag(R.id.tag_clickable_grid_view_adapter_holder) as VH
        val item = view.getTag(R.id.tag_clickable_grid_view_adapter_data) as T
        val position = view.getTag(R.id.tag_clickable_grid_view_adapter_position) as Int
        val pagePosition = getPosition(holder)
        if (position == RecyclerView.NO_POSITION) {
            // ignore
            return false
        }

        return onItemLongClick(view, item, position, pagePosition)
    }

    /**
     * Whether the adapter item can click
     *
     * @param position
     * @param pagePosition
     * @return
     */
    open fun clickable(position: Int, pagePosition: Int) = true

    /**
     * Whether the adapter item can long click
     *
     * @param position
     * @param pagePosition
     * @return
     */
    open fun longClickable(position: Int, pagePosition: Int) = true

    /**
     * Called when a item view has been clicked.
     *
     * @param view
     * @param item
     * @param position
     * @param pagePosition
     */
    open fun onItemClick(view: View, item: T, position: Int, pagePosition: Int) {
        // do nothing
    }

    /**
     * Called when a item view has been clicked and held.
     *
     * @param view
     * @param item
     * @param position
     * @param pagePosition
     * @return
     */
    open fun onItemLongClick(view: View, item: T, position: Int, pagePosition: Int) = false

    /**
     * Get the position of ViewHolder
     *
     * @param holder
     * @return
     */
    private fun getPosition(holder: VH) = holder.adapterPosition
}