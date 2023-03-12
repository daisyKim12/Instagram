package com.project.Instargram.kotlin.src.main.post.model

data class Result(
    val authorIdx: Int,
    val fileURLs: List<String>,
    val hashTagList: List<String>,
    val postIdx: Int,
    val postText: String,
    val taggedNickName: List<String>
)