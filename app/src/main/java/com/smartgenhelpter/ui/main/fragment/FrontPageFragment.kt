package com.smartgenhelpter.ui.main.fragment

import android.os.Bundle
import com.helper.base.BaseViewModel
import com.helper.ext.clickNoRepeat
import com.helper.ext.successToast
import com.helper.ext.toStartActivity
import com.helper.ext.toast
import com.smartgenhelpter.app.base.BaseFragment
import com.smartgenhelpter.databinding.FragmentFrontpageBinding
import com.smartgenhelpter.ui.login.activity.LoginActivity

/**
 * @Author smart_yq
 * @Date 2022-01-08
 * 描述 : 首页页面
 */
class FrontPageFragment : BaseFragment<BaseViewModel, FragmentFrontpageBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

        mBind.btStart.clickNoRepeat {
           toStartActivity(LoginActivity::class.java)
        }




    }

    override fun onBindViewClick() {



    }

    override fun onLoadRetry() {
        "1".successToast()
    }
}