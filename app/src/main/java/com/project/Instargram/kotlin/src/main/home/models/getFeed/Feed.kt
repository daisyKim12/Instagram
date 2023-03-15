package com.project.Instargram.kotlin.src.main.home.models.getFeed

data class Feed(
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
    val taggedUserList: List<String>,
    val isSavedPost: Boolean,
    val isLikedPost: Boolean,
    val commentNumber: Int
): java.io.Serializable