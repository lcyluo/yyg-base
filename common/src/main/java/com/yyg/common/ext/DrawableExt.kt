package com.yyg.common.ext

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.TextView

/**
 * 设置TextView图标
 */
fun TextView.setDrawable(
    leftResId: Int = -1,
    topResId: Int = -1,
    rightResId: Int = -1,
    bottomResId: Int = -1
) {
    val leftDrawable: Drawable? = leftResId.getDrawableByResId(this.context)
    val topDrawable: Drawable? = topResId.getDrawableByResId(this.context)
    val rightDrawable: Drawable? = rightResId.getDrawableByResId(this.context)
    val bottomDrawable: Drawable? = bottomResId.getDrawableByResId(this.context)
    this.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable)
}

fun TextView.setDrawableRelative(
    startResId: Int = -1,
    topResId: Int = -1,
    endResId: Int = -1,
    bottomResId: Int = -1
) {
    val startDrawable: Drawable? = startResId.getDrawableByResId(this.context)
    val topDrawable: Drawable? = topResId.getDrawableByResId(this.context)
    val endDrawable: Drawable? = endResId.getDrawableByResId(this.context)
    val bottomDrawable: Drawable? = bottomResId.getDrawableByResId(this.context)
    this.setCompoundDrawablesRelative(startDrawable, topDrawable, endDrawable, bottomDrawable)
}

fun Int.getDrawableByResId(context: Context?): Drawable? {
    if (context == null || this <= 0) return null
    val drawable = context.resources.getDrawable(this)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    return drawable
}

fun Int.getColorByResId(context: Context?): Int {
    if (context == null || this <= 0) return Color.TRANSPARENT
    return context.resources.getColor(this)
}