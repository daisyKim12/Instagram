package com.project.Instargram.kotlin.src.main.comment

import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyRequest
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyResponse

interface CommentInterface {

    fun onNewCommentSuccess(response: NewCommentResponse)

    fun onGetCommentSuccess(response: GetCommentResponse)

    fun onNewReplySuccess(response: NewReplyResponse)
}