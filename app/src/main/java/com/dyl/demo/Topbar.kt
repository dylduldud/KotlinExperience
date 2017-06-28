package com.dyl.demo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import org.jetbrains.anko.*

/**
 * Created by dengyulin on 2017/6/26.
 */
class Topbar : FrameLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        backgroundColor = Color.WHITE
        alpha=1f
        linearLayout {
            backgroundResource = R.drawable.r_button
            gravity = Gravity.CENTER
            padding=dip(5)
            imageView(R.mipmap.ico_search_grey).lparams(dip(15), dip(15)) {
                rightMargin = dip(5)
            }
            textView("中国大学MOOC") { textSize = 15f }
        }
        leftPadding=dip(20)
        rightPadding=dip(20)
        topPadding=dip(10)
        bottomPadding=dip(10)
    }

    fun setBackgroupAlpha(toAlpha: Float) {
        backgroundColor=Color.argb((toAlpha*255).toInt(),255,255,255)
    }
    fun setValueToBackgroupAlpha(from:Int=dip(0),to:Int=dip(160),now:Int){
        when(now){
            from->setBackgroupAlpha(0f)
            in from+1 .. to->setBackgroupAlpha(now/to.toFloat())
            else -> setBackgroupAlpha(1f)
        }
    }
}