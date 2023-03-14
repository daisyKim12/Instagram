package com.project.Instargram.kotlin.src.main.comment.model.newComment

data class NewCommentRequest(
    val userIdx: Int,
    val postIdx: Int,
    val userNickName: String,
    val commentText: String
)