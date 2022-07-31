package com.example.cameraalbum.adapter

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.cameraalbum.R
import com.example.cameraalbum.adapter.ImageAdapter.*
import com.example.cameraalbum.databinding.GallaryitemsBinding
import com.example.cameraalbum.model.Photo
import com.google.android.material.button.MaterialButton
import java.util.*


class ImageAdapter(val photoModel: List<Photo>) : RecyclerView.Adapter<recyclerViewHolder>() {

    lateinit var context: Context
    lateinit var binding: GallaryitemsBinding
    lateinit var list: List<String>

    inner class recyclerViewHolder(var view: GallaryitemsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        binding = GallaryitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return recyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {

        splitList(photoModel[position].photoName)
        binding.albumname.text = "Album${photoModel[position].id}"
        binding.album.setOnClickListener{
            dialog_Images(photoModel[position].photoName)
        }
    }


    fun splitList(photoString:String){
        list = listOf(*photoString.split(",").toTypedArray())
        Log.e("photolist","$list")
        if(list.isNotEmpty()) {
            if(list.size>2) {
                binding.albumicon.visibility = View.VISIBLE
                binding.albumname.visibility = View.VISIBLE
            }else {
                binding.albumicon.visibility = View.GONE
                binding.albumname.visibility = View.GONE
            }
            Glide.with(context).load(list[0]).into(binding.album)

        }

    }

    override fun getItemCount(): Int {
        return photoModel.size
    }

    fun dialog_Images(photoString: String)
    {
        val list_At_Position = listOf(*photoString.split(",").toTypedArray())
        Log.e("photolist","$list")

        val nagDialog = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        nagDialog.setCancelable(false)
        nagDialog.setContentView(R.layout.fragment_imageview)
        var viewPager = nagDialog.findViewById(R.id.viewpagerphoto) as ViewPager
        var btnClose: MaterialButton = nagDialog.findViewById(R.id.imageview_back) as MaterialButton
        val ivPreview: ImageView = nagDialog.findViewById(R.id.imageview) as ImageView

        Log.e("albumsize","${list_At_Position.size} ${list_At_Position}")
        if(list_At_Position.size>2){

            Log.e("albumsize1","${list_At_Position.size}")

            viewPager.visibility = View.VISIBLE
            ivPreview.visibility = View.GONE

            val adapter = MyPageAdapter(context,list_At_Position)
            viewPager.adapter = adapter

        }else {

            Log.e("albumsize2","${list_At_Position.size}")

            viewPager.visibility = View.GONE
            ivPreview.visibility = View.VISIBLE

            Glide.with(context).load(list_At_Position[0]).into(ivPreview)
        }




        btnClose.setOnClickListener{
            nagDialog.dismiss()
        }
        nagDialog.show()
    }

}