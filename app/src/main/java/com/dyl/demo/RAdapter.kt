package com.dyl.demo

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup


/**
 * Created by dengyulin on 2017/6/27.
 */

class RAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var list:List<BannerInfo> = listOf(BannerInfo("http://static1.tuicool.com/images/upload/w3cschool302.jpg"),BannerInfo("http://static0.tuicool.com/images/upload/meituan308.jpg"),BannerInfo("http://static2.tuicool.com/images/upload/ksyun334.jpg"),BannerInfo("http://static0.tuicool.com/images/upload/meituan308.jpg"),BannerInfo("http://static0.tuicool.com/images/upload/meituan308.jpg"))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return Banners(BannerView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is Banners->holder.banner.list=list
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
    private class Banners(val banner:BannerView) : ViewHolder(banner)
}



private class Sign(sign:View) : ViewHolder(sign)

private class IconList(iconList: View):ViewHolder(iconList)
