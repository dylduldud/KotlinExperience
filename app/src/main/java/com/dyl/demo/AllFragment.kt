package com.dyl.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by dengyulin on 2017/6/26.
 */
class AllFragment: Fragment() {
    lateinit var content:View
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AnkoLogger<AllFragment>().info { "AllFragment" }
        content=inflater?.inflate(R.layout.all_fragment,null)!!
        return content
    }
}