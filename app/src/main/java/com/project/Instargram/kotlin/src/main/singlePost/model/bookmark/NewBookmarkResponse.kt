package com.project.Instargram.kotlin.src.main.singlePost.model.bookmark

data class NewBookmarkResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)