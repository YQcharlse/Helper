package com.helper.ext

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


/**
 * @Author smart_yq
 * @Date  17:56
 */

/**
 * View显示
 */
fun View?.visible() {
    this?.visibility = View.VISIBLE
}

/**
 * View隐藏
 */
fun View?.gone() {
    this?.visibility = View.GONE
}

/**
 * View占位隐藏
 */
fun View?.inVisible() {
    this?.visibility = View.INVISIBLE
}


/**
 * View是否显示
 */
fun View?.isVisible(): Boolean {
    return this?.visibility == View.VISIBLE
}

/**
 * View是否隐藏
 */
fun View?.isGone(): Boolean {
    return this?.visibility == View.GONE
}

/**
 * View是否占位隐藏
 */
fun View?.isInVisible(): Boolean {
    return this?.visibility == View.INVISIBLE
}

/**
 * @param visible 如果为true 该View显示 否则隐藏
 */
fun View?.visibleOrGone(visible: Boolean) {
    if (visible) {
        this.visible()
    } else {
        this.gone()
    }
}

/**
 * @param visible 如果为true 该View显示 否则占位隐藏
 */
fun View?.visibleOrInvisible(visible: Boolean) {
    if (visible) {
        this.visible()
    } else {
        this.inVisible()
    }
}

/**
 * 显示传入的view集合
 */
fun visibleViews(vararg views: View?) {
    views.forEach {
        it?.visible()
    }
}

/**
 * 隐藏传入的view集合
 */
fun goneViews(vararg views: View?) {
    views.forEach {
        it?.gone()
    }
}


/**
 * view 渐变显示
 */
fun View.fadeIn() {
    if (this.visibility == View.VISIBLE) {
        return
    }
    val animation: Animation = AlphaAnimation(0f, 1f)
    animation.duration = 200
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            isEnabled = true
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    this.startAnimation(animation)
    this.visibility = View.VISIBLE
}

/**
 * view 渐变隐藏
 */
fun View.fadeOut() {
    if (this.visibility != View.VISIBLE) {
        return
    }
    // Since the button is still clickable before fade-out animation
    // ends, we disable the button first to block click.
    this.isEnabled = false
    val animation: Animation = AlphaAnimation(1f, 0f)
    animation.duration = 200
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            visibility = View.INVISIBLE
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    this.startAnimation(animation)
}

