package com.project.Instargram.kotlin.src.main.page.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding
import com.project.Instargram.kotlin.src.main.home.adpater.PostListener
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse

class MyPostAdapter(private val context: Context, private val response: GetUserPostResponse, private val photoListener: PhotoListener)
    : RecyclerView.Adapter<MyPostAdapter.SquareViewHolder>() {

    private var result = response.result

    inner class SquareViewHolder(private val binding: RvSquareItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
            Log.d(TAG, "bindItem: post->" + url)
        }

        fun bindClickable(postIdx: Int){
            binding.imgPost.setOnClickListener {
                photoListener.onPhotoClick(postIdx)
            }
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
        val reverse = result.size - position - 1
        var thumnailUrl = result[reverse].fileURLList[0]
        var postIdx = result[reverse].postIdx
        holder.bindItem(thumnailUrl)
        holder.bindClickable(postIdx)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    public interface PhotoListener {
        fun onPhotoClick(postIdx: Int)
    }
}