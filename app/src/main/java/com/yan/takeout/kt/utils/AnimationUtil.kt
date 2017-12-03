package com.yan.takeout.kt.utils

import android.view.animation.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/3 16:55
 *  @description : 动画工具类
 */
object AnimationUtil {
    private val DURATION = 600L

    fun getAnimationSet(isShow: Boolean): AnimationSet {
        val fromAlpha: Float; val toAlpha: Float
        val fromDegrees: Float; val toDegrees: Float
        val fromXValue: Float; val toXValue: Float
        if (isShow) {
            fromAlpha = 0f; toAlpha = 1f
            fromDegrees = 0f; toDegrees = 720f
            fromXValue = 2f; toXValue = 0f
        } else {
            fromAlpha = 1f; toAlpha = 0f
            fromDegrees = 720f; toDegrees = 0f
            fromXValue = 0f; toXValue = 2f
        }
        val animationSet = AnimationSet(true)
        val alphaAnimation = AlphaAnimation(fromAlpha, toAlpha)
        animationSet.addAnimation(alphaAnimation)

        val rotateAnimation = RotateAnimation(
                fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        animationSet.addAnimation(rotateAnimation)

        val translateAnimation = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromXValue,
                Animation.RELATIVE_TO_SELF, toXValue,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f)
        animationSet.addAnimation(translateAnimation)

        animationSet.duration = DURATION
        return animationSet
    }

    /**
     * 获取抛物线动画
     */
    fun getParabolaAnimation(srcLocation: IntArray, destLocation: IntArray): AnimationSet {
        val parabolaAnim = AnimationSet(false)
        parabolaAnim.duration = DURATION
        val translateX = TranslateAnimation(
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, destLocation[0].toFloat() - srcLocation[0].toFloat(),
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f)
        translateX.interpolator = LinearInterpolator()
        parabolaAnim.addAnimation(translateX)

        val translateY = TranslateAnimation(
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, destLocation[1].toFloat() - srcLocation[1].toFloat())
        translateY.interpolator = AccelerateInterpolator()
        parabolaAnim.addAnimation(translateY)

        return parabolaAnim
    }

}