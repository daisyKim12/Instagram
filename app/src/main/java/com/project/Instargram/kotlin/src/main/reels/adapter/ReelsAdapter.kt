package com.project.Instargram.kotlin.src.main.reels.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.VpReelsItemBinding
import com.project.Instargram.kotlin.src.main.reels.model.Reels

class ReelsAdapter(private val context: Context, private val reels: List<Reels>)
    : RecyclerView.Adapter<ReelsAdapter.ReelsViewHolder>(){

    inner class ReelsViewHolder(private val binding: VpReelsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(reels: Reels) {
            Glide.with(context).load(reels.videoUrl).into(binding.video)
            Glide.with(context).load(reels.tumbnailUrl).into(binding.imgThumbnail)
            binding.txtName.text = reels.name
            binding.txtComment.text = reels.commentNum.toString()
            binding.txtLike.text = reels.like.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelsViewHolder {
        return ReelsViewHolder(
            VpReelsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReelsViewHolder, position: Int) {
        holder.bindItem(reels[position])
    }

    override fun getItemCount(): Int {
        return reels.size
    }
}