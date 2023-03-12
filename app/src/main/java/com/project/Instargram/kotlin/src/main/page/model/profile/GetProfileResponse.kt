package com.project.Instargram.kotlin.src.main.page.model.profile

data class GetProfileResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)