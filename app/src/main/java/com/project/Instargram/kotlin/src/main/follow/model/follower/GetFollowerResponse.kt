package com.project.Instargram.kotlin.src.main.follow.model.follower

data class GetFollowerResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)