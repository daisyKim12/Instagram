package com.project.Instargram.kotlin.src.main.extra.model

data class GetSinglePostResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
): java.io.Serializable