package com.project.Instargram.kotlin.src.main.follow.model.following

data class GetFollowingResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)