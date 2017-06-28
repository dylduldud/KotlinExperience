package com.dyl.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by dengyulin on 2017/6/26.
 */
class HomeFragment: Fragment() {
    lateinit var content: View
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AnkoLogger<HomeFragment>().info { "HomeFragment" }
        content=inflater?.inflate(R.layout.home_fragment,null)!!
        content.recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        content.recyclerView.apply {addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                content.topbar.setValueToBackgroupAlpha(now = computeVerticalScrollOffset())
            }
        })}
        content.recyclerView.adapter=RAdapter()
        return content
    }
}