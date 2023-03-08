package com.project.Instargram.kotlin.src.main.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.Instargram.kotlin.databinding.RvPostImageRetouchItemBinding
import com.project.Instargram.kotlin.src.main.post.model.Retouch

class RetouchAdapter(private val retouch: List<Retouch>)
    : RecyclerView.Adapter<RetouchAdapter.RetouchViewHolder>() {


    inner class RetouchViewHolder(private val binding: RvPostImageRetouchItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(retouch: Retouch) {
            binding.txtTitle.text = retouch.title
            binding.img.setImageResource(retouch.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetouchViewHolder {
        return RetouchViewHolder(
            RvPostImageRetouchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RetouchViewHolder, position: Int) {
        holder.bindItem(retouch[position])
    }

    override fun getItemCount(): Int {
        return retouch.size
    }
}