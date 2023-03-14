package com.project.Instargram.kotlin.src.main.comment.model.newReply

data class NewReplyResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)