package com.example.galleryapp.fragments.home

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.constants.GlideApp
import com.example.galleryapp.databinding.ImagesItemBinding

class ImageViewHolder (private val binding: ImagesItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(photo: Photo) {
        binding.photo = photo
        loadImage(binding.img,photo.src.original)
        //Toast.makeText(binding.root.context,photo.src.original,Toast.LENGTH_LONG).show()
        //GlideApp.with(binding.root.context).load(photo.src.original).into(binding.img)
    }
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String) {
            GlideApp.with(imageView.context).load(url).into(imageView)
        }
    }
}