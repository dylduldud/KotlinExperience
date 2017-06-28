package com.dyl.demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.dyl.demo.utils.inject.width
import com.ppx.kotlin.utils.inject.imagePath
import org.jetbrains.anko.dip
import kotlin.properties.Delegates

/**
 * Created by dengyulin on 2017/6/27.
 */
class BannerView : RecyclerView {
    private var lineWidth = dip(20).toFloat()
    private var paint = Paint()
    private var margin = dip(2).toFloat()
    private var startLeft = 0f
    private var marginTop = dip(120).toFloat()
    private var pos=0
    var list: List<BannerInfo> by Delegates.observable(listOf()) { _, _, newValue ->
        (adapter as BannerAdapter).list = newValue
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        //宽 - 总条数*线宽 - （总条数-1）* 边距
        if (list != null)
            startLeft = (measuredWidth - (lineWidth * list!!.size) - (list!!.lastIndex * margin)) / 2

    }

    private fun init() {
        PagerSnapHelper().attachToRecyclerView(this)
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false) as LayoutManager?
        adapter = BannerAdapter()
        paint.reset()
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dip(2).toFloat()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        if (list != null)
            list!!.forEachIndexed { index, bannerInfo ->
                val left = startLeft + (index * lineWidth + margin)
                if (index == pos) paint.color = Color.WHITE else paint.color = resources.getColor(R.color.green)
                canvas!!.drawLine(left, marginTop, left + lineWidth, marginTop, paint)
            }
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        pos=Math.round(computeHorizontalScrollOffset()/getChildAt(0).measuredWidth.toFloat())

    }
}

data class BannerInfo(val uri: String)

private class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    var pos = 0
    var list: List<BannerInfo> by Delegates.observable(listOf()) { _, a, b ->
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerViewHolder?, position: Int) {
        holder!!.pic.imagePath = list[position].uri
    }

    override fun getItemCount(): Int = list.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BannerAdapter.BannerViewHolder {
        return BannerViewHolder(ImageView(parent!!.context).apply {
            layoutParams = ViewGroup.LayoutParams(context.width, dip(160))
        })
    }


    class BannerViewHolder(val pic: ImageView) : RecyclerView.ViewHolder(pic)
}