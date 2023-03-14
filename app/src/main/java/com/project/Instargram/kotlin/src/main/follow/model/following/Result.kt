package com.project.Instargram.kotlin.src.main.follow.model.following

data class Result(
    val userIdx: Int,
    val userName: String,
    val userNickName: String,
    val userProfileImgURL: String
)