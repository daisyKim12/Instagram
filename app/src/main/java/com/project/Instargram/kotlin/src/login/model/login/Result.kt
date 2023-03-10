package com.project.Instargram.kotlin.src.login.model.login

data class Result(
    val jwtToken: String,
    val userIdx: Int
)