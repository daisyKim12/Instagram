package com.project.Instargram.kotlin.src.main.home.models.getFeed

data class GetPostResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)