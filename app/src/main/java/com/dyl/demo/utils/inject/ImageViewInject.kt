package com.ppx.kotlin.utils.inject

import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by dengyulin on 2017/6/16.
 */
fun ImageView.setImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageButton.setImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

var ImageView.imagePath:String
    set(value) {
        setImageUrl(value)
    }
    get() {return ""}

var ImageButton.imagePath:String
    set(value) {
        setImageUrl(value)
    }
    get() {return ""}
