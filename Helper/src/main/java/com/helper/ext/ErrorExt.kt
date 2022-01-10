package com.helper.ext

import com.google.gson.JsonSyntaxException
import com.helper.R
import kotlinx.coroutines.TimeoutCancellationException
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @Author smart_yq
 * @Date  17:56
 */

val Throwable.code: Int
    get() {
        val errorCode = when (this) {
            is HttpStatusCodeException -> this.statusCode // Http状态码异常
            is ParseException -> this.errorCode     // 业务code异常
            else -> "-1"
        }
        return try {
            errorCode.toString().toInt()
        } catch (e: Exception) {
            -1
        }
    }

val Throwable.msg: String
    get() {
        return if (this is UnknownHostException) {
            //网络异常
            getStringExt(R.string.helper_net_error_no)
        } else if (
            this is SocketTimeoutException  //okHttp全局设置超时
            || this is TimeoutException     //方法超时
            || this is TimeoutCancellationException  //协程超时
        ) {
            getStringExt(R.string.helper_net_error_timeout)

        } else if (this is ConnectException) {
            getStringExt(R.string.helper_net_error_strong)

        } else if (this is HttpStatusCodeException) {
            getStringExt(R.string.helper_net_error_http)
        } else if (this is JsonSyntaxException) {
            //请求成功，但Json语法异常,导致解析失败
            getStringExt(R.string.helper_net_error_parsing)
        } else if (this is ParseException) {
            // ParseException异常表明请求成功，但是数据不正确 msg为空，显示code
            this.message ?: errorCode
        } else {
            getStringExt(R.string.helper_net_error_err)
        }
    }


