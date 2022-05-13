package com.smartgenhelpter.ui.login.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.helper.ext.getStringExt
import com.helper.ext.showLoadingExt
import com.helper.ext.warningToast
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.smartgenhelpter.R
import com.smartgenhelpter.app.base.BaseActivity
import com.smartgenhelpter.app.ext.initBack
import com.smartgenhelpter.databinding.ActivityInformationBinding
import com.smartgenhelpter.ui.login.viewmodel.LoginViewModel

class InformationActivity : BaseActivity<LoginViewModel, ActivityInformationBinding>() {


    override fun initObserver() {

        mToolbar.initBack(
            leftIcon = R.drawable.ic_back,
            rightStr = "",
            onLeftClick = { finish() }, onRightClick = {})

    }


    override fun initView(savedInstanceState: Bundle?) {

        mBind.etInfoUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                mBind.tvUsernameSize.text = s.length.toString()
            }
        })


    }

    inner class InforClickProxy() {

        fun chose() {

        }

        fun submit() {
            showLoadingExt(getStringExt(R.string.Loading))
        }
    }


    /**
     * 图选择
     */
    private fun choicePhotoWrapper() {
        val perms = arrayOf(
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.CAMERA
        )

        XXPermissions.with(this).permission(perms).request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                if (all) {

                } else {
                    //部分权限未正常授予
                    getStringExt(R.string.permission_dis).warningToast()
                    XXPermissions.startPermissionActivity(this@InformationActivity, permissions);
                }
            }

            override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                if (never) {
                    //被永久拒绝授权，请手动授予权限
                    getStringExt(R.string.permission_dis).warningToast()
                } else {
                    //获取权限失败
                    getStringExt(R.string.permission_dis).warningToast()
                    XXPermissions.startPermissionActivity(this@InformationActivity, permissions);
                }
            }

        })


    }


    fun loadImage(url: String) {
        // 显示圆形的 ImageView
        if (url.contains("/0/")) {
            Glide.with(this)
                .load(url)
                .transform(MultiTransformation(CenterCrop(), CircleCrop()))
                .placeholder(R.drawable.image_placeholder)
                .into(mBind.ivInformationAvatar)
        } else {
            Glide.with(this)
                .load(url)
                .transform(MultiTransformation(CenterCrop(), CircleCrop()))
                .placeholder(R.drawable.image_placeholder)
                .into(mBind.ivInformationAvatar)
        }

    }

}