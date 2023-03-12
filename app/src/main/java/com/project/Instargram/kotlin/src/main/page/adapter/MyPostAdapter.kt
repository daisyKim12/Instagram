package com.project.Instargram.kotlin.src.main.page.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse

class MyPostAdapter(private val context: Context, private val response: GetUserPostResponse)
    : RecyclerView.Adapter<MyPostAdapter.SquareViewHolder>()  {

    private var thumbnailList = response.result

    inner class SquareViewHolder(private val binding : RvSquareItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SquareViewHolder {
        return SquareViewHolder(
            RvSquareItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        var thumnailUrl = thumbnailList[position].fileURLList[0]
        holder.bindItem(thumnailUrl)
    }

    override fun getItemCount(): Int {
        return thumbnailList.size
    }
}