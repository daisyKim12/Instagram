package com.project.Instargram.kotlin.src.main.comment.model.getComment

data class GetCommentResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)