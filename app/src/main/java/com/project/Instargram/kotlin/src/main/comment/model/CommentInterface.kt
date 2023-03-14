package com.project.Instargram.kotlin.src.main.comment.model

import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse

interface CommentInterface {

    fun onNewCommentSuccess(response: NewCommentResponse)

    fun onGetCommentSuccess(response: GetCommentResponse)
//
//    fun onNewComment2CommentSuccess(response: NewCommentResponse)
//
//    fun onGetComment2CommentSuccess(response: NewCommentResponse)
}