package com.project.Instargram.kotlin.src.main.singlePost.model.follow

data class NewFollowResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)