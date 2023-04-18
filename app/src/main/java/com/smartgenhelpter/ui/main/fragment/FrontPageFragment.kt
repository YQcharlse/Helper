package com.smartgenhelpter.ui.main.fragment

import android.os.Bundle
import com.helper.base.BaseViewModel
import com.helper.ext.clickNoRepeat
import com.smartgenhelpter.app.base.BaseFragment
import com.smartgenhelpter.databinding.FragmentFrontpageBinding

/**
 * @Author smart_yq
 * @Date 2022-01-08
 * 描述 : 首页页面
 */
class FrontPageFragment : BaseFragment<BaseViewModel, FragmentFrontpageBinding>() {


    override fun initView(savedInstanceState: Bundle?) {

        mBind.btStart.clickNoRepeat {
        }

    }
}