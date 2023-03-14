package com.project.Instargram.kotlin.src.main.singlePost.model.bookmark

data class NewBookmarkRequest(
    val targetPostIdx: Int,
    val userIdx: Int
)