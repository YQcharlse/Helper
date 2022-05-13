package com.smartgenhelpter.app.api

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
object NetUrl {


    // 服务器请求成功的 Code值
    const val SUCCESS_CODE = 200

    //设置为默认域名
    @DefaultDomain
    const val DEV_URL = "http://117.160.143.178:91/yewu/"

    //登录
    const val LOGIN = "login"

}