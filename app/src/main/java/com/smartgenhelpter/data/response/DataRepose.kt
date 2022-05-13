package com.smartgenproduct.data.response

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

/**
 * @Author smart_yq
 * @Date 2021-12-14
 * 描述 :
 */


@Parcelize
data class DataRepose(
    var doclist: ArrayList<dacBean>
) : Parcelable {
    @Parcelize
    data class dacBean(
        var docName: String,
        var docdesc: String,
        var docModel: String,
        var product_img: String,
        var docPath: String,
        var checked: Boolean = false,
        var visible: Boolean = false
    ) : Parcelable, BaseObservable()

}