package com.project.Instargram.kotlin.src.login.model.EmailAuth

data class EmailDuplicateResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ResultDuplicatEmail
)