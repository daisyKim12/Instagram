package com.project.Instargram.kotlin.src.main.page.model.profile

data class Result(
    val followerNumber: Int,
    val followingNumber: Int,
    val postNumber: Int,
    val profileImgUrl: String,
    val userIdx: Int,
    val userName: String,
    val userNickName: String
)