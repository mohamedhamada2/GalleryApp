package com.example.galleryapp.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.ImagesItemBinding


class ImagesAdapter (private val list: ArrayList<Photo>,private val context: Context): RecyclerView.Adapter<ImageViewHolder>() {
    private lateinit var binding: ImagesItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        binding = ImagesItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return   ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = list[position]
        holder.bind(photo)
    }
    fun add_photo(PhotoList: ArrayList<Photo>) {
        for (photo in PhotoList) {
            PhotoList.add(photo)
        }
        notifyDataSetChanged()
    }
}