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