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

import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.kevin.gridpager.extras.binding.BindingGridViewAdapter

/**
 * CategoryItemAdapter
 *
 * @author zwenkai@foxmail.com, Created on 2019-11-21 19:03:08
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class CategoryItemAdapter : BindingGridViewAdapter<CategoryBean>() {

    override val layoutRes: Int
        get() = R.layout.layout_category_item

    override fun setVariable(binding: ViewDataBinding, item: CategoryBean, position: Int) {
        binding.setVariable(BR.model, item)
    }

    override fun onItemClick(view: View, item: CategoryBean, position: Int, pagePosition: Int) {
        Toast.makeText(
            view.context,
            "position = $position, pagePosition = $pagePosition, ${item.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

}