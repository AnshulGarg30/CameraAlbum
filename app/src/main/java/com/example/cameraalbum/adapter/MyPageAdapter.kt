package com.example.cameraalbum.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.cameraalbum.R
import java.util.*


class MyPageAdapter(val context: Context,val imageList: List<String>) : PagerAdapter() {

    var mLayoutInflater: LayoutInflater? = null

    init {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View? = mLayoutInflater?.inflate(R.layout.fragment_pageradapter, container, false)
        val imageView: ImageView = itemView?.findViewById<View>(R.id.imageview_) as ImageView
        Log.e("ddd","positions${position} ${imageList[position]}")
        Glide.with(context).load(imageList[position].replaceFirst(" ","")).placeholder(context.getDrawable(R.drawable.ic_launcher_background)).into(imageView)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)

    }

}