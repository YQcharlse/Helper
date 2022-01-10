package com.smartgencloud.app.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
class HeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //模拟了2个公共参数
//        builder.addHeader("os", "android").build()
        return chain.proceed(builder.build())
    }

}