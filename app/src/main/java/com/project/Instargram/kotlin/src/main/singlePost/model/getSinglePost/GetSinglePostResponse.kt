package com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost

data class GetSinglePostResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
): java.io.Serializable