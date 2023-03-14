package com.project.Instargram.kotlin.src.main.comment.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.CommentMyPostItemBinding
import com.project.Instargram.kotlin.databinding.CommentUserItemBinding
import com.project.Instargram.kotlin.databinding.RvRectangleItemBinding
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding
import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse

class CommentAdapter(private val context: Context, private val preComment: GetSinglePostResponse, private val getCommentResponse: GetCommentResponse)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class PreCommentViewHolder(private val binding : CommentMyPostItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(preComment: GetSinglePostResponse) {
            val result = preComment.result
            Glide.with(context).load(result.authorProfileImgURL).into(binding.imgThumbnail)
            binding.txtName.text = result.authorNickName
            binding.txtTime.text = result.since.toString()
            binding.txtDetail.text = result.postText

        }
    }

    inner class CommentViewHolder(private val binding: CommentUserItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(result: com.project.Instargram.kotlin.src.main.comment.model.getComment.Result) {
            Glide.with(context).load(result.profileImgURL).into(binding.imgThumbnail)
            binding.txtName.text = result.authorNickName
            binding.txtTime.text = result.since.toString()
            binding.txtDetail.text = result.commentText

            binding.txtC2cNumber.text = "답글 " + result.commentReplyCount.toString() +"개 보기"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0) {
            return PreCommentViewHolder(
                CommentMyPostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return CommentViewHolder(
                CommentUserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0) {
            (holder as PreCommentViewHolder).bindItem(preComment)
        } else {
            //position - 1
            val result = getCommentResponse.result[position-1]
            (holder as CommentViewHolder).bindItem(result)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) {
            return 0
        } else {
            return 1
        }
    }

    override fun getItemCount(): Int {
        return getCommentResponse.result.size + 1
    }

//    public interface PhotoListener {
//        fun onPhotoClick(postIdx: Int)
//    }
}