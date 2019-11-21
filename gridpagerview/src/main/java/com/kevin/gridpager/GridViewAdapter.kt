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

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * GridViewAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2019-05-03 10:54:09
 * Major Function：<b>GridViewAdapter</b>
 * <p/>
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
abstract class GridViewAdapter<T, VH : RecyclerView.ViewHolder> {

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [RecyclerView.ViewHolder.itemView] to reflect the item at the given position.
     *
     * @param holder        The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position      The position of the item within the adapter's data set.
     * @param pagePosition  The position of the item within the page`s data set.
     * @param item          The data of current position.
     */
    abstract fun onBindViewHolder(holder: VH, position: Int, pagePosition: Int, item: T)
}
