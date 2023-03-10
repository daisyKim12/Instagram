package com.project.Instargram.kotlin.src.login.model.login

data class EmailLoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)