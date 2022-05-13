package com.smartgenhelpter.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.language.MultiLanguages;

/**
 * @Author smart_yq
 * @Date 2021-12-27
 * 描述 :
 */
public class LanguagesWebView extends WebView {

    public LanguagesWebView(@NonNull Context context) {
        this(context, null);
    }

    public LanguagesWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LanguagesWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 修复 WebView 初始化时会修改 Activity 语种配置的问题
        MultiLanguages.updateAppLanguage(context);
    }
}
