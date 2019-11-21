package com.kevin.gridpager.sample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.kevin.dialog.BaseDialog
import com.kevin.wheel.WheelView

/**
 * SelectorDialog
 *
 * @author zwenkai@foxmail.com, Created on 2019-01-27 20:33:32
 * Major Function：**底部弹窗选择框**
 *
 *
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class SelectorDialog : BaseDialog() {
    private var mListener: OnSelectListener? = null
    private lateinit var mBinding: SelectorDialogBinding
    private var mTitle: String? = null
    private lateinit var mDataList: MutableList<Int>
    private var mSelectedPosition = 0

    override fun createView(
        context: Context?,
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): View {
        mBinding = SelectorDialogBinding.inflate(layoutInflater, viewGroup, false)
        mBinding.view = this
        return mBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        mBinding.titleView.text = mTitle
        mBinding.wheelView.setDataItems(mDataList)
        mBinding.wheelView.setSelectedItemPosition(mSelectedPosition, false)
        mBinding.wheelView.setOnItemSelectedListener(object :
            WheelView.OnItemSelectedListener {
            override fun onItemSelected(
                wheelView: WheelView,
                data: Any,
                position: Int
            ) {
                mSelectedPosition = position
            }
        })
    }

    /**
     * 设置中间的标题
     *
     * @param title
     * @return
     */
    fun setTitle(title: String?): SelectorDialog {
        mTitle = title
        return this
    }

    /**
     * 设置数据
     *
     * @param dataList
     * @return
     */
    fun setData(dataList: MutableList<Int>): SelectorDialog {
        mDataList = dataList
        return this
    }

    /**
     * 设置初始选择的下标
     *
     * @param position
     * @return
     */
    fun setSelectedItemPosition(position: Int): SelectorDialog {
        mSelectedPosition = position
        return this
    }

    /**
     * 设置初始选择的下标
     *
     * @param data
     * @return
     */
    fun setSelectedItem(data: Int): SelectorDialog {
        val index = mDataList.indexOf(data)
        if (index != -1) {
            mSelectedPosition = index
        }
        return this
    }

    /**
     * "取消"按钮点击的监听回调
     *
     * @param view
     */
    fun onCancelClick(view: View?) {
        dismiss()
    }

    /**
     * "确定"按钮点击的监听回调
     *
     * @param view
     */
    fun onConfirmClick(view: View?) {
        mListener?.onSelect(mDataList[mSelectedPosition])
        dismiss()
    }

    /**
     * 显示弹窗
     *
     * @param activity
     * @param listener
     * @return
     */
    fun show(activity: FragmentActivity, listener: OnSelectListener?): SelectorDialog {
        mListener = listener
        super.show(activity.supportFragmentManager, TAG)
        return this
    }

    /**
     * 选择的监听接口
     */
    interface OnSelectListener {
        /**
         * 选择的监听回调
         *
         * @param data
         */
        fun onSelect(data: Int)
    }

    companion object {
        const val TAG = "SelectorDialog"
        // 设置宽度为屏幕宽度
        // 设置黑色透明背景
        // 设置动画
        val instance: SelectorDialog
            get() {
                val dialog = SelectorDialog()
                dialog.setGravity(Gravity.BOTTOM) // 设置宽度为屏幕宽度
                    .setWidth(1f) // 设置黑色透明背景
                    .setDimEnabled(true)
                    .setBackgroundColor(Color.WHITE) // 设置动画
                    .setAnimations(android.R.style.Animation_InputMethod)
                return dialog
            }
    }
}