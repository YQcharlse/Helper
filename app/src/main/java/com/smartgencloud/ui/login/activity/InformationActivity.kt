package com.smartgencloud.ui.login.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.helper.ext.*
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.smartgencloud.R
import com.smartgencloud.app.base.BaseActivity
import com.smartgencloud.app.ext.initBack
import com.smartgencloud.app.util.GlideEngine
import com.smartgencloud.databinding.ActivityInformationBinding
import com.smartgencloud.ui.login.viewmodel.LoginViewModel
import java.util.*

class InformationActivity : BaseActivity<LoginViewModel, ActivityInformationBinding>() {


    private var selectList: ArrayList<LocalMedia> = arrayListOf()


    override fun initObserver() {
        super.initObserver()

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
                    PictureSelector.create(this@InformationActivity)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .isWeChatStyle(true) // 是否开启微信图片选择风格
                        .maxSelectNum(1) // 最大图片选择数量
                        .minSelectNum(1) // 最小选择数量
                        .isAndroidQTransform(true)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                        .isMaxSelectEnabledMask(true) // 选择数到了最大阀值列表是否启用蒙层效果
                        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) // 设置相册Activity方向，不设置默认使用系统
                        .selectionMode(PictureConfig.SINGLE) // 多选 or 单选
                        .isZoomAnim(true) // 图片列表点击 缩放效果 默认true
                        .freeStyleCropEnabled(true)//裁剪框是否可拖拽
                        .forResult(object : OnResultCallbackListener<LocalMedia> {
                            override fun onResult(result: List<LocalMedia>) {
                                selectList.clear()
                                loadImage(result[0].realPath)
                                selectList.addAll(result)
                            }

                            override fun onCancel() {

                            }
                        })

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