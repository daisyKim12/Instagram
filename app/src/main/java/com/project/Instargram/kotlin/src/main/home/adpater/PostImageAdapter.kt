package com.project.Instargram.kotlin.src.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.VpHomePostImageItemBinding

class PostImageAdapter(private val context: Context, private val thumbnailUrlList: List<String>)
    : RecyclerView.Adapter<PostImageAdapter.PostImageViewHolder>(){

    inner class PostImageViewHolder(private val binding: VpHomePostImageItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        return PostImageViewHolder(
            VpHomePostImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        holder.bindItem(thumbnailUrlList[position])
    }

    override fun getItemCount(): Int {
        return thumbnailUrlList.size
    }
}