package com.project.Instargram.kotlin.src.login.model.newaccount

data class NewAccountResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ResultImage
)