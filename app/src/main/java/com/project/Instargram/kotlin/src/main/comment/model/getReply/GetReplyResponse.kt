package com.project.Instargram.kotlin.src.main.comment.model.getReply

data class GetReplyResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)