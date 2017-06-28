package com.dyl.demo.utils.inject

import android.content.Context

/**
 * Created by dengyulin on 2017/6/27.
 */
var Context.width:Int
    set(value) {}
    get() {return  resources.displayMetrics.widthPixels}
var Context.height:Int
    set(value) {}
    get() {return  resources.displayMetrics.heightPixels}