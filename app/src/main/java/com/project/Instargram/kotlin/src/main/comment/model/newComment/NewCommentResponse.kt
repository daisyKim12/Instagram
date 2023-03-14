package com.project.Instargram.kotlin.src.main.comment.model.newComment

data class NewCommentResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)