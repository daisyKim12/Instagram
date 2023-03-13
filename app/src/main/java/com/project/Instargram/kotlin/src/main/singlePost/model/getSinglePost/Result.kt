package com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost

data class Result(
    val authorIdx: Int,
    val authorNickName: String,
    val authorProfileImgURL: String,
    val hashTagList: List<String>,
    val likeNumber: Int,
    val location: String,
    val postFileURLList: List<String>,
    val postIdx: Int,
    val postText: String,
    val since: Int,
    val taggedUserList: List<String>
): java.io.Serializable