package com.project.Instargram.kotlin.src.main.home.models

data class Post(
//    val tumbnailUrl: String,
//    val name: String,
//    val postUrl: String,
//    val like: Int,
//    val detailUrl: String,
//    val commentNum: Int,
//    val time: String

    val tumbnailUrl: String,
    val name: String,
    val postUrlList: List<String>,
    val like: Int,
    val detailUrl: String,
    val commentNum: Int,
    val time: String
)
