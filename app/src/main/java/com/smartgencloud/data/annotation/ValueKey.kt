package com.smartgencloud.data.annotation

/**
 * @Author smart_yq
 * @Date 2021-11-06
 * 描述　:
 */
object ValueKey {

    // MMKV 的 key
    const val MMKV_APP_KEY = "smartgen_cloud_app"

    const val isFirst = "app_is_first_open"
    const val isLogin = "isLogin"
    const val language = "language"


    //user
    const val userPhoto = "userPhoto"
    const val userToken = "userToken"
    const val userId = "userid"
    const val userPassword = "userPassword"
    const val userNickname = "userNickname"
    const val userPhone = "userPhone"
    const val userEmail = "userEmail"

    const val UmdeviceToken = "deviceToken"

    //message
    const val alarmFlag = "alarmflag"
    const val statusFlag = "statusflag"
    const val actionFlag = "actionflag"

    //map
    const val maptype = "maptype"
    const val outLandMap = "outland"
    const val inLandMap = "inland"

    //Genstatus
    const val GenStutas = "GenStutas"
    const val Genes = "0"

    const val isChina = "isChina"

}