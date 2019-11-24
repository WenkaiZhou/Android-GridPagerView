package com.kevin.gridpager.sample.binding

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.kevin.gridpager.extras.binding.BindingGridViewAdapter
import com.kevin.gridpager.extras.binding.BindingViewHolder
import com.kevin.gridpager.sample.BR
import com.kevin.gridpager.sample.R
import com.kevin.gridpager.sample.entity.Category
import java.util.*

/**
 * BindingCategoryAdapter
 *
 * @author zhouwenkai@baidu.com, Created on 2019-11-24 10:40:37
 *         Major Function：<b>条目Adapter</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class BindingCategoryAdapter : BindingGridViewAdapter<Category>() {

    private val random = Random()

    override val layoutRes: Int
        get() = R.layout.layout_binding_category_item

    override fun configureViewHolder(holder: BindingViewHolder) {
        val binding = holder.getBinding<CategoryAdapterBinding>()
        binding.ivImage.setBackgroundColor(getBackgroundColor())
    }

    override fun setVariable(binding: ViewDataBinding, item: Category, position: Int) {
        binding.setVariable(BR.model, item)
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

}