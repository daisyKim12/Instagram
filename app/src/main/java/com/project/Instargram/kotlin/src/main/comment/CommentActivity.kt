package com.project.Instargram.kotlin.src.main.comment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityCommentBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.comment.adapter.CommentAdapter
import com.project.Instargram.kotlin.src.main.comment.model.CommentInterface
import com.project.Instargram.kotlin.src.main.comment.model.CommentService
import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentRequest
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse
import com.project.Instargram.kotlin.src.main.search.adapter.SearchStaggeredAdapter
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse

class CommentActivity:BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate), CommentInterface {

    private val KEY_USERID = "userIdx"
    private val KEY_PRE_COMMENT = "data_for_comment"
    private val KEY_IMAGE = "image_path"
    private val KEY_NICK_NAME = "nickName"
    private var imagePath = ""

    private var userIdx = 0
    private var postIdx = 0
    private var userNickName = ""

    private lateinit var getSinglePostResponse: GetSinglePostResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagePath = getStringValue(KEY_IMAGE).toString()
        userIdx = getIntegerValue(KEY_USERID)!!

        getSinglePostResponse = intent.extras?.getSerializable(KEY_PRE_COMMENT) as GetSinglePostResponse
        postIdx = getSinglePostResponse.result.postIdx
        userNickName = getStringValue(KEY_NICK_NAME).toString()

        setPreData(getSinglePostResponse)

        CommentService(this).tryGetComment(postIdx, userIdx)
    }

    override fun onResume() {
        super.onResume()
        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.btnToWhomClose.setOnClickListener {
            binding.clMyCommentToWhom.collapse()
        }
        binding.btnMyComment.setOnClickListener {
            //댓글 달기
            val commentText = binding.edittextMyComment.text.toString()
            val newCommentRequest = NewCommentRequest(userIdx, postIdx, userNickName, commentText)
            CommentService(this).tryNewComment(newCommentRequest)
        }
    }

    override fun onNewCommentSuccess(response: NewCommentResponse) {
        Log.d(TAG, "onNewCommentSuccess: " + response)
    }

    override fun onGetCommentSuccess(response: GetCommentResponse) {
        Log.d(TAG, "onGetCommentSuccess: " + response)
        setCommentRecyclerView(getSinglePostResponse, response)
    }

    private fun setCommentRecyclerView(response: GetSinglePostResponse, getCommentResponse: GetCommentResponse) {
        binding.rvComment.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvComment.layoutManager = linearLayoutManager
        val adapter = CommentAdapter(this, response, getCommentResponse)
        adapter.notifyDataSetChanged()
        binding.rvComment.adapter = adapter
    }

    private fun setPreData(response: GetSinglePostResponse) {
        binding.txtToWhome.text = response.result.authorNickName + "님에게 답글 남기는 중"
        Glide.with(this).load(imagePath).into(binding.imgMyProfile)
    }
}