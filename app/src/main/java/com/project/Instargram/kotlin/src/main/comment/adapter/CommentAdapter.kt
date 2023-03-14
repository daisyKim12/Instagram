package com.project.Instargram.kotlin.src.main.comment.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.CommentMyPostItemBinding
import com.project.Instargram.kotlin.databinding.CommentUserItemBinding
import com.project.Instargram.kotlin.src.main.comment.ReplyInterface
import com.project.Instargram.kotlin.src.main.comment.CommentService
import com.project.Instargram.kotlin.src.main.comment.ReplyService
import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.getReply.GetReplyResponse
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyRequest
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse

class CommentAdapter(private val context: Context, private val preComment: GetSinglePostResponse,
                     private val getCommentResponse: GetCommentResponse, private val userIdx: Int, private val commentListener: CommentListener)
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
        : RecyclerView.ViewHolder(binding.root), ReplyInterface {

        fun bindItem(result: com.project.Instargram.kotlin.src.main.comment.model.getComment.Result) {
            Glide.with(context).load(result.profileImgURL).into(binding.imgThumbnail)
            binding.txtName.text = result.authorNickName
            binding.txtTime.text = result.since.toString()
            binding.txtDetail.text = result.commentText
            binding.txtC2cNumber.text = "답글 " + result.commentReplyCount.toString() +"개 보기"

            binding.clC2c.setOnClickListener {
                binding.clC2c.visibility = View.INVISIBLE
                binding.clC2c.isClickable = false

                ReplyService(this).tryGetReply(result.postIdx, result.commentIdx, userIdx)
            }
        }

        fun bindClickable(rootIdx: Int, targetNickName: String) {
            binding.btnAddComment.setOnClickListener {
                commentListener.onCommentClick(rootIdx, targetNickName)
            }
        }

        override fun onGetReplySuccess(response: GetReplyResponse) {
            Log.d(TAG, "onGetReplySuccess: " + response)
            setReplyRecyclerView(response)
        }

        fun setReplyRecyclerView(response: GetReplyResponse) {
            binding.rvC2c.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvC2c.layoutManager = linearLayoutManager
            val adapter = ReplyAdapter(context, response)
            adapter.notifyDataSetChanged()
            binding.rvC2c.adapter = adapter
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
            (holder as CommentViewHolder).bindClickable(result.commentIdx, result.authorNickName)
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

    public interface CommentListener {
        fun onCommentClick(rootIdx: Int, targetNickName: String)
    }
}