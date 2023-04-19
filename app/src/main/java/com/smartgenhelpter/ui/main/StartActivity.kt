package com.smartgenhelpter.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import com.helper.base.BaseViewModel
import com.helper.ext.countDownCoroutines
import com.helper.ext.finishAllActivity
import com.helper.ext.getStringExt
import com.helper.ext.toStartActivity
import com.hjq.language.MultiLanguages
import com.lxj.xpopup.XPopup
import com.smartgenhelpter.R
import com.smartgenhelpter.app.base.BaseActivity
import com.smartgenhelpter.app.ext.mmkv
import com.smartgenhelpter.data.annotation.ValueKey
import com.smartgenhelpter.databinding.ActivityStartBinding

/**
 * @Author: smartgen_yq
 * @Data: 2022-01-08
 * 描述: 闪屏界面
 */

class StartActivity : BaseActivity<BaseViewModel, ActivityStartBinding>() {


    lateinit var mDownTimer: CountDownTimer

    override fun showToolBar() = false


    override fun initView(savedInstanceState: Bundle?) {



        //显示广告图片，开始倒计时
        mDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                toNextActivity()
            }
        }


        if (mmkv.getBoolean(ValueKey.isFirst, true)) {
            //mmkv.putBoolean(ValueKey.isFirst, false)

            XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .asConfirm(
                    getString(R.string.start_first_title),
                    getStringExt(R.string.start_first_context),
                    getStringExt(R.string.start_first_cancle),
                    getStringExt(R.string.start_first_confir),
                    {
                        mDownTimer.start()
                    },
                    {
                        finishAllActivity()
                    },
                    false,
                    R.layout.layout_popup_center
                ).show()


        } else {
            mDownTimer.start()
        }


        // 是否跟随系统的语种
        if (MultiLanguages.isSystemLanguage()) {
            MultiLanguages.clearAppLanguage(this)
        }


    }


    fun toNextActivity() {
        toStartActivity(MainActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mDownTimer.isInitialized) {
            mDownTimer.cancel()
        }
    }

}