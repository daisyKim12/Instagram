package com.project.Instargram.kotlin.src.main.comment.model.getReply

data class Result(
    val authorNickName: String,
    val postIdx: Int,
    val profileImgURL: String,
    val replyIdx: Int,
    val replyText: String,
    val rootIdx: Int,
    val since: Int
)