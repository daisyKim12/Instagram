package com.project.Instargram.kotlin.src.main.home.models.getFeed

data class Result(
    val feedList: List<Feed>,
    val followInfoList: List<FollowInfo>
)