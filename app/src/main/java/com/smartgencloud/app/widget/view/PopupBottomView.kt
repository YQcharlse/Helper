package com.smartgencloud.app.widget.view

import android.content.Context
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.helper.ext.dp2px
import com.helper.ext.getColorExt
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.interfaces.OnSelectListener
import com.smartgencloud.R


/**
 * @Author smart_yq
 * @Date 2022-01-08
 * 描述 : 底部类型弹窗
 */
class PopupBottomView(
    context: Context,
    private val title: String,
    private val navList: ArrayList<String>
) : BottomPopupView(context) {


    override fun getImplLayoutId(): Int = R.layout.layout_popup_bottom

    lateinit var recy: RecyclerView
    lateinit var ivClose: AppCompatImageView
    lateinit var tvPopupTitle: TextView


    private var strCheck: String = ""

    override fun onCreate() {
        super.onCreate()

        recy = findViewById(R.id.rcy_popup_feedback)
        ivClose = findViewById(R.id.iv_popup_feedback_dis)
        tvPopupTitle = findViewById(R.id.tv_popup_title)

        tvPopupTitle.text = title

        recy.linear().divider {
            setMargin(20, 20)
            setDivider(dp2px(0.5F))
            setColor(getColorExt(R.color.grey_e2e))
        }.setup {
            addType<String>(R.layout.item_popup_bottom)

            onBind {
                findView<TextView>(R.id.tv_item_bottom).text = getModel()
            }

            R.id.tv_item_bottom.onClick {
                val item = getModel<String>()
                strCheck = item
                selectListener!!.onSelect(modelPosition, strCheck)
                dismiss()
            }

        }

        recy.models = navList

        ivClose.setOnClickListener {
            dismiss()
        }


    }

    private var selectListener: OnSelectListener? = null

    fun setOnSelectListener(selectListener: OnSelectListener): BottomPopupView {
        this.selectListener = selectListener
        return this
    }


}