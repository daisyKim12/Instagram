package com.project.Instargram.kotlin.src.main.singlePost.model.like

data class NewLikeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)