package com.smartgencloud.ui.login.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.helper.ext.getColorExt
import com.helper.ext.getStringExt
import com.helper.ext.isEqualStr
import com.lxj.xpopup.XPopup
import com.smartgencloud.R
import com.smartgencloud.app.base.BaseActivity
import com.smartgencloud.app.ext.initBack
import com.smartgencloud.app.manage.InputTextManager
import com.smartgencloud.app.widget.view.PopupBottomView
import com.smartgencloud.databinding.ActivityLoginBinding
import com.smartgencloud.ui.login.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {


    override fun initObserver() {
        super.initObserver()

        mToolbar.initBack(rightStr = "", rightIcon = null, onLeftClick = {}, onRightClick = {})


        InputTextManager.with(this)
            .addView(mDataBind.retLoginPassword)
            .addView(mDataBind.retLoginUsername)
            .setMain(mDataBind.btLogin)
            .build()

    }

    override fun initView(savedInstanceState: Bundle?) {

        mDataBind.viewModel = mViewModel
        mDataBind.click = LoginClickProxy()


        val spannable = SpannableStringBuilder(getStringExt(R.string.login_protocol))

        if (isEqualStr(getStringExt(R.string.current_language), "English")) {
            spannable.setSpan(UserAgreement(), 24, 38, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(PrivacyAgreement(), 43, 57, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            spannable.setSpan(UserAgreement(), 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(PrivacyAgreement(), 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        mDataBind.tvLoginProtocol.run {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }

    }


    inner class LoginClickProxy {

        //区域选择
        fun area() {
            val navList = arrayListOf(
                getStringExt(R.string.login_area_cn),
                getStringExt(R.string.login_area_hy),
            )

            XPopup.Builder(this@LoginActivity)
                .isDestroyOnDismiss(true)
                .asCustom(PopupBottomView(
                    this@LoginActivity,
                    getStringExt(R.string.login_area_title), navList
                ).setOnSelectListener { position, text ->
                    mDataBind.setLoginArea.leftText = text
                }).show()

        }


        //注册
        fun register() {}

        //忘记密码
        fun forget() {

        }

        //登录
        fun login() {

        }

        //协议
        fun protocol() {
            mDataBind.cbLoginProtocol.isChecked = !mDataBind.cbLoginProtocol.isChecked
        }
    }


    /**
     * 隐私协议点击事件
     */
    private class PrivacyAgreement : ClickableSpan() {
        override fun onClick(widget: View) {


        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = getColorExt(R.color.blue_005)
        }
    }

    /**
     * 用户协议点击事件
     */
    private class UserAgreement : ClickableSpan() {
        override fun onClick(widget: View) {

        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = getColorExt(R.color.blue_005)
        }

    }

}