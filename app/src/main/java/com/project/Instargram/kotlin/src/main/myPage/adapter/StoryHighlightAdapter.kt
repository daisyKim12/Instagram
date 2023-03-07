package com.project.Instargram.kotlin.src.main.myPage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvHomeStoryItemBinding
import com.project.Instargram.kotlin.databinding.RvMypageStoryItemBinding
import com.project.Instargram.kotlin.src.main.home.adpater.StoryAdapter
import com.project.Instargram.kotlin.src.main.home.models.Story

class StoryHighlightAdapter(private val context: Context, private val urlList: List<String>)
    : RecyclerView.Adapter<StoryHighlightAdapter.StoryHighLightviewHolder>() {


    inner class StoryHighLightviewHolder(private val binding: RvMypageStoryItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgThumbnail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHighLightviewHolder {
        return StoryHighLightviewHolder(
            RvMypageStoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryHighLightviewHolder, position: Int) {
        holder.bindItem(urlList[position])
    }

    override fun getItemCount(): Int {
        return urlList.size
    }
}