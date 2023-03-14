package com.project.Instargram.kotlin.src.main.comment.model.newReply

data class NewReplyRequest(
    val userIdx: Int,
    val postIdx: Int,
    val rootCommentIdx: Int,
    val userNickName: String,
    val replyText: String
)