package com.project.Instargram.kotlin.src.login.model.login

data class EmailLoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val messag: String,
    val result: Result
)