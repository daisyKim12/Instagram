package com.project.Instargram.kotlin.src.main.comment.model.newComment

data class Result(
    val authorNickName: String,
    val commentIdx: Int,
    val commentReplyCount: Int,
    val commentText: String,
    val postIdx: Int,
    val profileImgURL: String,
    val rootIdx: Int,
    val since: Long
)