package com.project.Instargram.kotlin.src.login.model.PhoneAuth

data class PhoneDuplicateResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String
)