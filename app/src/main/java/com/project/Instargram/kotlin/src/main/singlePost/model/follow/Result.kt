package com.project.Instargram.kotlin.src.main.singlePost.model.follow

data class Result(
    val isApproved: Boolean,
    val isCreateSuccess: Boolean,
    val targetIdx: Int
)