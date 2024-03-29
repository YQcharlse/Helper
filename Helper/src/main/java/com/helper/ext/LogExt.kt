package com.helper.ext

import com.helper.util.XLog
import com.hjq.toast.Toaster


fun Any?.logV(tag: String? = null) =
    XLog.logType(XLog.V, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logD(tag: String? = null) =
    XLog.logType(XLog.D, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logI(tag: String? = null) =
    XLog.logType(XLog.I, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logW(tag: String? = null) =
    XLog.logType(XLog.W, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logE(tag: String? = null) =
    XLog.logType(XLog.E, tag, XLog.STACK_TRACE_INDEX_7, this)

fun String?.logJson(tag: String? = null) =
    XLog.logXmlOrJson(XLog.JSON, tag, XLog.STACK_TRACE_INDEX_7, this)

fun String?.toast() {
    Toaster.show(this)
}
