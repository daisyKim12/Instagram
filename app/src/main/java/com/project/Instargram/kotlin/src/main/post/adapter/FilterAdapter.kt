package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvHomeStoryItemBinding
import com.project.Instargram.kotlin.databinding.RvPostImageFilterItemBinding
import com.project.Instargram.kotlin.src.main.home.models.Story
import com.project.Instargram.kotlin.src.main.post.model.Filter

class FilterAdapter(private val context: Context, private val filter: List<Filter>, private val url: String)
    : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {


    inner class FilterViewHolder(private val binding: RvPostImageFilterItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(filter: Filter) {
            binding.txtTitle.text = filter.title
            Glide.with(context).load(url).into(binding.imgPost)
            //use filter.style to change style
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            RvPostImageFilterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bindItem(filter[position])
    }

    override fun getItemCount(): Int {
        return filter.size
    }
}