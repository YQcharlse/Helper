package com.smartgencloud.app.util

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * @Author smart_yq
 * @Date 2021-12-25
 * 描述 :
 */
class ChangeBase {

    fun bitmapToBase64(bitmap: Bitmap): String {
        var result = ""
        val baos = ByteArrayOutputStream()
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            baos.flush()
            baos.close()
            val bitmapBytes = baos.toByteArray()
            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                baos.flush()
                baos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return result
    }

}