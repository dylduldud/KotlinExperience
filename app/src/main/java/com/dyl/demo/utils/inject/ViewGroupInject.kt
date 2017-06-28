package com.ppx.kotlin.utils.inject

import android.view.View
import android.view.ViewGroup

/**
 * Created by dengyulin on 2017/6/19.
 */

fun ViewGroup.replace(view: View){
    if(indexOfChild(view)==-1) {
        addView(view)
    }
}
fun ViewGroup.removeOthers(view: View){
    (0..childCount - 1)
            .map { getChildAt(it) }
            .filter { it !=view }
            .forEach { removeView(it) }
}
fun ViewGroup.removeOthers(index:Int){
    (0..childCount - 1)
            .filter { it !=index }
            .forEach { removeViewAt(it) }
}
