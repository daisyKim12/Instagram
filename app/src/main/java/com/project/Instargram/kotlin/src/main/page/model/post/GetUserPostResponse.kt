package com.project.Instargram.kotlin.src.main.page.model.post

data class GetUserPostResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)