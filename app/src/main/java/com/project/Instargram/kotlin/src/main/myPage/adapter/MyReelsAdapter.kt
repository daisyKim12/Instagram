package com.project.Instargram.kotlin.src.main.myPage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvRectangleShortItemBinding

class MyReelsAdapter(private val context: Context, private val rectangle: List<String>)
    : RecyclerView.Adapter<MyReelsAdapter.RectangleViewHolder>()  {

    inner class RectangleViewHolder(private val binding : RvRectangleShortItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RectangleViewHolder {
        return RectangleViewHolder(
            RvRectangleShortItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
        holder.bindItem(rectangle[position])
    }

    override fun getItemCount(): Int {
        return rectangle.size
    }
}