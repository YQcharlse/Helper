package com.smartgenhelpter.ui.login.viewmodel

import com.helper.base.BaseViewModel
import com.helper.core.StringObservableField

class LoginViewModel : BaseViewModel() {


    //账户名
    val userName = StringObservableField()

    //密码
    val password = StringObservableField()

    //验证码
    val code = StringObservableField()

    //新密码
    val newPassword = StringObservableField()


}