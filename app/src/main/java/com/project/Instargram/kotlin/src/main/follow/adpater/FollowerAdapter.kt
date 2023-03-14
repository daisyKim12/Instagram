package com.project.Instargram.kotlin.src.main.follow.adpater

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvFollowerItemBinding
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding
import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse

class FollowerAdapter(private val context: Context, private val getFollowerResponse: GetFollowerResponse)
    : RecyclerView.Adapter<FollowerAdapter.ViewHolder>() {

    private var result = getFollowerResponse.result

    inner class ViewHolder(private val binding: RvFollowerItemBinding) :
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
    ): ViewHolder {
        return ViewHolder(
            RvFollowerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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