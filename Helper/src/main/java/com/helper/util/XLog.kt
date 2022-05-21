package com.helper.util

import com.drake.logcat.LogCat
import com.google.gson.Gson
import com.helper.net.interception.logging.util.LogUtils
import java.io.PrintWriter
import java.io.StringWriter


/**
 * @Author smart_yq
 * @Date 2021-11-03
 * 描述　: 打印日志开关，框架是否打印请求日志、输出Log日志 默认为 true 打印数据
 */
var SmartGenHelperLog: Boolean = true
    set(value) {
        field = value
        LogCat.setDebug(value)
        XLog.init(value)
        LogUtils.setLog(value)
    }

object XLog {

    private val gson by lazy {
        Gson()
    }

    private const val NULL_TIPS = "Log with null object"
    private const val DEFAULT_MESSAGE = "execute"
    private const val TAG_DEFAULT = "XLog"
    private const val SUFFIX = ".java"
    private const val KT = ".kt"

    const val V = 0x1
    const val D = 0x2
    const val I = 0x3
    const val W = 0x4
    const val E = 0x5
    const val JSON = 0x7

    private const val STACK_TRACE_INDEX_5 = 5
    private const val STACK_TRACE_INDEX_4 = 4

    //LogExt扩展函数
    const val STACK_TRACE_INDEX_7 = 7

    private var mGlobalTag: String? = null
    private var mIsGlobalTagEmpty = true
    private var IS_SHOW_LOG = true

    fun init(isShowLog: Boolean) {
        IS_SHOW_LOG = isShowLog
    }

    fun init(isShowLog: Boolean, tag: String?) {
        IS_SHOW_LOG = isShowLog
        mGlobalTag = tag
        mIsGlobalTagEmpty = mGlobalTag.isNullOrEmpty()
    }

    fun v() {
        printLog(V, null, STACK_TRACE_INDEX_5, DEFAULT_MESSAGE)
    }

    fun v(msg: Any?) {
        printLog(V, null, STACK_TRACE_INDEX_5, msg)
    }

    fun v(tag: String?, msg: Any?) {
        logType(V, tag, STACK_TRACE_INDEX_5, msg)
    }

    fun d() {
        printLog(D, null, STACK_TRACE_INDEX_5, DEFAULT_MESSAGE)
    }

    fun d(msg: Any?) {
        printLog(D, null, STACK_TRACE_INDEX_5, msg)
    }

    fun d(tag: String?, msg: Any?) {
        logType(D, tag, 6, msg)
    }

    fun i() {
        printLog(I, null, STACK_TRACE_INDEX_5, DEFAULT_MESSAGE)
    }

    fun i(msg: Any?) {
        printLog(I, null, STACK_TRACE_INDEX_5, msg)
    }

    fun i(tag: String?, msg: Any?) {
        logType(I, tag, STACK_TRACE_INDEX_5, msg)
    }

    fun w() {
        printLog(W, null, STACK_TRACE_INDEX_5, DEFAULT_MESSAGE)
    }

    fun w(msg: Any?) {
        printLog(W, null, STACK_TRACE_INDEX_5, msg)
    }

    fun w(tag: String?, msg: Any?) {
        logType(W, tag, STACK_TRACE_INDEX_5, msg)
    }

    fun e() {
        printLog(E, null, STACK_TRACE_INDEX_5, DEFAULT_MESSAGE)
    }

    fun e(msg: Any?) {
        printLog(E, null, STACK_TRACE_INDEX_5, msg)
    }

    fun e(tag: String?, msg: Any?) {
        logType(E, tag, STACK_TRACE_INDEX_5, msg)
    }


    fun json(jsonFormat: String?) {
        printLog(JSON, null, STACK_TRACE_INDEX_5, jsonFormat)
    }

    fun json(tag: String?, jsonFormat: String?) {
        logXmlOrJson(JSON, tag, STACK_TRACE_INDEX_5, jsonFormat)
    }

    fun trace() {
        printStackTrace()
    }

    private fun printStackTrace() {
        if (!IS_SHOW_LOG) return
        val tr = Throwable()
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        val message = sw.toString()
        val traceString = message.split("\\n\\t").toTypedArray()
        val sb = StringBuilder()
        sb.append("\n")
        for (trace in traceString) {
            if (trace.contains("at com.socks.library.KLog")) {
                continue
            }
            sb.append(trace).append("\n")
        }
        val contents =
            wrapperContent(STACK_TRACE_INDEX_4, null, sb.toString())
        val tag = contents[0].toString()
        val msg = contents[1]
        val headString = contents[2]
        LogCat.d(headString + msg, tag)
    }

    private fun printLog(type: Int, tagStr: String?, stackTraceIndex: Int, msg: Any?) {
        if (!IS_SHOW_LOG) return
        val contents =
            wrapperContent(stackTraceIndex, tagStr, msg)
        val tag = contents[0].toString()
        val msgValue = contents[1].toString()
        val headString = contents[2].toString()
        when (type) {
            V -> LogCat.v(headString + msgValue, tag)
            D -> LogCat.d(headString + msgValue, tag)
            I -> LogCat.i(headString + msgValue, tag)
            W -> LogCat.w(headString + msgValue, tag)
            E -> LogCat.e(headString + msgValue, tag)
            E -> LogCat.e(headString + msgValue, tag)
            JSON -> LogCat.json(msgValue, tag, headString)
        }
    }

    fun logType(type: Int, tag: String?, stackTraceIndex: Int, msg: Any?) {
        if (tag.isNullOrEmpty() && msg != null) {
            printLog(type, null, stackTraceIndex, msg)
        } else if (!tag.isNullOrEmpty() && msg != null) {
            printLog(type, tag, stackTraceIndex - 1, msg)
        } else if (!tag.isNullOrEmpty() && msg == null) {
            printLog(type, null, stackTraceIndex, tag)
        } else {
            printLog(type, null, stackTraceIndex, DEFAULT_MESSAGE)
        }
    }

    fun logXmlOrJson(type: Int, tag: String?, stackTraceIndex: Int, xmlOrJson: String?) {
        if (tag.isNullOrEmpty() && !xmlOrJson.isNullOrEmpty()) {
            printLog(type, null, stackTraceIndex, xmlOrJson)
        } else if (!tag.isNullOrEmpty() && !xmlOrJson.isNullOrEmpty()) {
            printLog(type, tag, stackTraceIndex, xmlOrJson)
        } else if (!tag.isNullOrEmpty() && xmlOrJson.isNullOrEmpty()) {
            printLog(type, null, stackTraceIndex, tag)
        } else {
            printLog(type, null, stackTraceIndex, DEFAULT_MESSAGE)
        }
    }

    private fun wrapperContent(
        stackTraceIndex: Int,
        tagStr: String?,
        msg: Any?
    ): Array<String?> {
        val stackTrace =
            Thread.currentThread().stackTrace
        val targetElement = stackTrace[stackTraceIndex]
        var className = targetElement.className
        val lastFileType =
            if (targetElement.fileName.endsWith(SUFFIX)) SUFFIX else KT
        val classNameInfo = className.split(".").toTypedArray()
        if (classNameInfo.isNotEmpty()) {
            className = classNameInfo[classNameInfo.size - 1] + lastFileType
        }
        if (className.contains("$")) {
            className = className.split("$").toTypedArray()[0] + lastFileType
        }
        val methodName = targetElement.methodName
        var lineNumber = targetElement.lineNumber
        if (lineNumber < 0) {
            lineNumber = 0
        }
        var tag = tagStr ?: className
        if (mIsGlobalTagEmpty && tag.isNullOrEmpty()) {
            tag = TAG_DEFAULT
        } else if (!mIsGlobalTagEmpty) {
            tag = mGlobalTag
        }
        val msgValue = if (msg == null) NULL_TIPS else getObjectsString(msg)
        val headString =
            "[ ($className:$lineNumber)#$methodName ] "
        return arrayOf(tag, msgValue, headString)
    }

    private fun getObjectsString(msg: Any?): String? {
        return if (msg is String) msg else gson.toJson(msg)
    }
}