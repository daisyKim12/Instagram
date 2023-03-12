package com.project.Instargram.kotlin.src.main.post.model

data class NewPostResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)