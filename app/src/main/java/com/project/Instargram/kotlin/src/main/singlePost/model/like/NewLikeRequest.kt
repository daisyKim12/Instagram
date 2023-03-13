package com.project.Instargram.kotlin.src.main.singlePost.model.like

data class NewLikeRequest(
    val postIdx: Int,
    val userIdx: Int
)