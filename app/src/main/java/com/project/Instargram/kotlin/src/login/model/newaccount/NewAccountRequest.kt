package com.project.Instargram.kotlin.src.login.model.newaccount

data class NewAccountRequest(
    val birthday: String,
    val email: String,
    val name: String,
    val nickName: String,
    val password: String
)