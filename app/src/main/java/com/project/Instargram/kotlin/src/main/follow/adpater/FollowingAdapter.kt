package com.project.Instargram.kotlin.src.main.follow.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvFollowingItemBinding
import com.project.Instargram.kotlin.src.main.follow.model.following.GetFollowingResponse

class FollowingAdapter(private val context: Context, private val getFollowingResponse: GetFollowingResponse)
    : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private var result = getFollowingResponse.result

    inner class FollowingViewHolder(private val binding: RvFollowingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String, nickname: String) {
            Glide.with(context).load(url).into(binding.imgThumbnail)
            binding.txtName.text = nickname
        }

//        fun bindClickable(postIdx: Int){
//            binding.imgPost.setOnClickListener {
//                photoListener.onPhotoClick(postIdx)
//            }
//        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingViewHolder {
        return FollowingViewHolder(
            RvFollowingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        var thumnailUrl = result[position].userProfileImgURL
        var nickname = result[position].userNickName
        holder.bindItem(thumnailUrl, nickname)
    }

    override fun getItemCount(): Int {
        return result.size
    }

//    public interface PhotoListener {
//        fun onPhotoClick(postIdx: Int)
//    }
}