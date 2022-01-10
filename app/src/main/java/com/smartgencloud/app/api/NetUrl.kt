package com.smartgencloud.app.api

import com.smartgencloud.app.ext.mmkv
import com.smartgencloud.data.annotation.ValueKey
import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述 :
 */
object NetUrl {


    @DefaultDomain //设置为默认域名
    @JvmField
    var DEV_URL =
        if (mmkv.getBoolean(ValueKey.isChina, true))
            "http://www.smartgencloudplus.com/"
        else
            "http://www.smartgencloudplus.cn/"

    //device url
    @Domain(name = "socket", className = "socket")
    @JvmField
    var SOCKET_URL =
        if (mmkv.getBoolean(ValueKey.isChina, true))
            "ws://www.smartgencloudplus.com:8080/ws"
        else
            "ws://www.smartgencloudplus.cn:8080/ws"

    @Domain(name = "device", className = "device")
    @JvmField
    var DEVICE_URL =
        if (mmkv.getBoolean(ValueKey.isChina, true))
            "http://www.smartgencloudplus.com:8080/"
        else
            "http://www.smartgencloudplus.cn:8080/"


//    @DefaultDomain //设置为默认域名
//    const val DEV_URL = "http://117.158.18.33:3000"
//    //device url
//    @Domain(name = "socket", className = "socket")
//    const val SOCKET_URL = "ws://117.158.18.33:3002/ws";
//    //device url
//    @Domain(name = "device", className = "device")
//    const val DEVICE_URL = "http://117.158.18.33:3002/"

    //获取闪屏界面
    const val getWelcome = "welcomjson"

    //获取轮播图
    const val getAdvertising = "carouseljson"

    //获取首页产品
    const val getStartProduct = "startproductjson"

    //登录
    const val LOGIN = "phoneloginjson"

    //机组移交事项列表
    const val getTransferList = "transferlistjson"

    //首页图表
    const val getSetAlarm = "gensetgeneratejson"

    //退出
    const val getLogut = "phonelogoutjson"

    //设置头像
    const val getPhotoImage = "upuserphotojson"

    //修改密码
    const val getChangepw = "changepwjson"

    //账号是否存在  用户注册接口
    const val getIsRegister = "registercapjson"

    //用户重置密码接口
    const val getFindPassword = "findpasswordcapjson"

    //获取验证码接口(验证码超时时长５分钟)
    const val getCode = "getcaptcha"

    //修改账户接口
    const val setAccount = "upaccountjson"

    //消息设置
    const val getMessageSet = "upmessagejson"

    //意见反馈
    const val getOpinionSet = "feedbackaddjson"

    //修改昵称
    const val getNickNameSet = "upnamejson"

    //获取云猫型号
    const val getCatModel = "modeljson"

    //获取控制器品牌
    const val getBrandModel = "brandjson"

    //获取控制器型号
    const val getControlModel = "modulejson"

    //获取通讯方式
    const val getCommunicationModel = "portjson"

    //获取通讯方式
    const val getBaudRateModel = "baudjson"

    //添加机组
    const val getAddGen = "gensetaddjson"

    //查看修改机组
    const val getSubscribeGen = "subscribejson"

    //转让设备
    const val getTransfer = "transferdevicejson"

    //转让设备
    const val getTranfficQuery = "iotcard/getinfo"

    //在线升级
    const val getdeviceupgrade = "/devicedata/updatasetmodem"

    //机组列表
    const val getGenList = "gensetlistjson"

    //机组用户权限
    const val getGenUserAll = "gensetuserallauthjson"

    //机组信息修改
    const val getGenEditInfo = "genseteditjson"

    //机组信息退订
    const val getGenUnsubscribe = "unsubscribejson"

    //机组信息删除
    const val getGenUnDel = "gensetdeletejson"

    //机组信息修改
    const val getGenCodeEdit = "codeeditjson"

    //机组监控状态接口
    const val getGenStatus = "devicedata/getstatus"

    //控制机组
    const val getGenSendAction = "devicedata/sendaction"

    //控制机组
    const val getModemRestart = "devicedata/restartmodem"

    //获取数据
    const val getGenSendMonitor = "devicedata/getmonitor"

    //还原实时数据设置
    const val getGenSendReset = "deviceitems/reset"

    //修改实时数据设置
    const val getGenSendEdit = "deviceitems/edit"

    //获取报警/历史数据需要的数据
    const val getGenMonitorSelect = "devicedata/getmonitorwithselect"

    //保存报警设置
    const val getGenItemsSelect = "deviceitems/selectphone"

    //获取报警分析数据
    const val getGenAlarmHist = "devicedata/getalarmhistory"

    //历史数据
    const val getGenHistory = "devicedata/gethistory"

    //获取操作日志
    const val getGenSendULog = "gensetlogjson"

    //获取单个机组信息
    const val getGenInfo = "gensetinfojson"

    //获取单台机组运行时间和发电量接口
    const val getGenRunning = "gensetrunningtimejson"

    //地图json
    const val getMapList = "gensetmaplistjson"

    //地图json
    const val getMapSearchList = "gensetmapsearchjson"

    //权限列表
    const val getPermissionList = "gensetalluserauthjson"

    //修改权限
    const val editPermission = "gensetusereditjson"


    //云平台切换同步账号接口
    const val setArea = "syncaccountjson"


    //云平台切换同步账号接口
    const val checkLogin = "checkloginjson"


    //更新
    const val updateVersion = "versioncheckjson"


}