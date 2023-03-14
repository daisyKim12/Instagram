package com.project.Instargram.kotlin.src.main.comment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.CommentC2cItemBinding
import com.project.Instargram.kotlin.databinding.RvFollowerItemBinding
import com.project.Instargram.kotlin.src.main.comment.model.getReply.GetReplyResponse
import com.project.Instargram.kotlin.src.main.follow.adpater.FollowerAdapter
import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse

class ReplyAdapter(private val context: Context, private val response: GetReplyResponse)
    : RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {

    val result = response.result

    inner class ViewHolder(private val binding: CommentC2cItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(result: com.project.Instargram.kotlin.src.main.comment.model.getReply.Result) {
            Glide.with(context).load(result.profileImgURL).into(binding.imgThumbnail)
            binding.txtName.text = result.authorNickName
            binding.txtTime.text = result.since.toString()
            binding.txtDetail.text = result.replyText
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            CommentC2cItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(result[position])
    }

    override fun getItemCount(): Int {
        return result.size
    }

//    public interface PhotoListener {
//        fun onPhotoClick(postIdx: Int)
//    }
}