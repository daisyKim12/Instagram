package com.project.Instargram.kotlin.src.main.search.model

data class GetWithoutSearchResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)