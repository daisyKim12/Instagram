package com.project.Instargram.kotlin.src.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvHomePostItemBinding
import com.project.Instargram.kotlin.src.main.home.models.Post

class PostAdapter(private val context: Context, private val post: List<Post>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    inner class PostViewHolder(private val binding: RvHomePostItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(post: Post) {
//            Glide.with(context).load(post.tumbnailUrl).into(binding.imgThumbnail)
//            binding.txtName.text = post.name
//            Glide.with(context).load(post.postUrl).into(binding.rvPost)
//            binding.txtLike.text = "좋아요 " + post.like.toString() + "개"
//            binding.txtDetail.text = post.name + " " + post.detailUrl
//            binding.txtComment.text = "댓글" + post.commentNum.toString() + "개 보기"


            //binding.txtTime.text = ??
            //TODO("add click listener")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            RvHomePostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindItem(post[position])
    }

    override fun getItemCount(): Int {
        return post.size
    }
}