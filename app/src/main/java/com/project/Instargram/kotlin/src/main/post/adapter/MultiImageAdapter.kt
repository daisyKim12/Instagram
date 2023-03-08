package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvPostStyleMutliBinding

class MultiImageAdapter(private val context: Context, private val imageList: List<String>)
    : RecyclerView.Adapter<MultiImageAdapter.MultiImagesViewHolder>() {


    inner class MultiImagesViewHolder(private val binding: RvPostStyleMutliBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.img)
            //use filter.style to change style
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiImagesViewHolder {
        return MultiImagesViewHolder(
            RvPostStyleMutliBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MultiImagesViewHolder, position: Int) {
        holder.bindItem(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}