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
import com.smartgencloud.R
import com.smartgencloud.app.base.BaseActivity
import com.smartgencloud.app.ext.initBack
import com.smartgencloud.app.manage.InputTextManager
import com.smartgencloud.databinding.ActivityRegisterBinding
import com.smartgencloud.ui.login.viewmodel.LoginViewModel

class RegisterActivity : BaseActivity<LoginViewModel, ActivityRegisterBinding>() {

    override fun initObserver() {
        super.initObserver()


        mToolbar.initBack(
            leftIcon = R.drawable.ic_back,
            onLeftClick = {
                finish()
            }, onRightClick = {})


        InputTextManager.with(this)
            .addView(mBind.etRegPhone)
            .addView(mBind.cvGetCode)
            .addView(mBind.etRegPassword)
            .addView(mBind.etRegNewPassword)
            .setMain(mBind.btReg)
            .build()
    }


    override fun initView(savedInstanceState: Bundle?) {

        mBind.viewModel = mViewModel
        mBind.click = RegisterClickProxy()


        val spannable = SpannableStringBuilder(getStringExt(R.string.login_protocol))

        if (isEqualStr(getStringExt(R.string.current_language), "English")) {
            spannable.setSpan(UserAgreement(), 24, 38, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(PrivacyAgreement(), 43, 57, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            spannable.setSpan(UserAgreement(), 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(PrivacyAgreement(), 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        mBind.tvRegisterProtocol.run {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }


    }


    inner class RegisterClickProxy {


        //获取验证码
        fun getCode() {

        }

        //注册
        fun reg() {

        }

        //协议
        fun protocol() {
            mBind.cbRegisterProtocol.isChecked = !mBind.cbRegisterProtocol.isChecked
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