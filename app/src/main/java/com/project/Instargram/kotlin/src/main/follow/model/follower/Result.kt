package com.project.Instargram.kotlin.src.main.follow.model.follower

data class Result(
    val isAlsoFollowing: Boolean,
    val userIdx: Int,
    val userName: String,
    val userNickName: String,
    val userProfileImgURL: String
)