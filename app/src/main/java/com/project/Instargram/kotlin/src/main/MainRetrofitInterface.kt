package com.project.Instargram.kotlin.src.main

import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.getReply.GetReplyResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentRequest
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyRequest
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyResponse
import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse
import com.project.Instargram.kotlin.src.main.follow.model.following.GetFollowingResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface MainRetrofitInterface {

    //new post
    @Multipart
    @POST("posts")
    fun postNewPost(@Part data: MultipartBody.Part, @Part image: List<MultipartBody.Part>)
            : Call<NewPostResponse>

    //feed
    @GET("posts/near-feed/{userIdx}?")
    fun getFeed(
        @Path("userIdx") userIdx: Int,
        @Query("page-num") page_num: Int
    ): Call<GetPostResponse>

    //search
    @GET("posts/all-post?")
    fun getWithoutSearch(
        @Query("userIdx") userIdx: Int,
        @Query("page-num") page_num: Int
    ): Call<GetWithoutSearchResponse>

    //my page
    @GET("users/profiles/{requestUserIdx}?")
    fun getProfile(
        @Path("requestUserIdx") requestUserIdx: Int,
        @Query("targetUserIdx") targetUserIdx: Int
    ): Call<GetProfileResponse>

    @GET("users/profiles/{requestUserIdx}/written-posts")
    fun getUserPost(
        @Path("requestUserIdx") requestUserIdx: Int,
        @Query("targetUserIdx") targetUserIdx: Int
    ): Call<GetUserPostResponse>

    //특정 게시물 가져오기
    @GET("posts/detail?")
    fun getSinglePost(
        @Query("userIdx") userIdx: Int,
        @Query("postIdx") postIdx: Int
    ): Call<GetSinglePostResponse>

    //팔로우 요청
    @POST("users/user-follow")
    fun postNewFollow(
        @Body request: NewFollowRequest
    ): Call<NewFollowResponse>

    @PATCH("users/user-follow")
    fun patchUnFollow(
        @Body request: NewFollowRequest
    ): Call<NewFollowResponse>

    //좋이요 요청
    @POST("users/post-like")
    fun postNewLike(
        @Body request: NewLikeRequest
    ): Call<NewLikeResponse>

    @PATCH("users/post-like")
    fun patchUnLike(
        @Body request: NewLikeRequest
    ): Call<NewLikeResponse>

    //북마크 요청
    @POST("users/save-post")
    fun postNewBookmark(
        @Body request: NewBookmarkRequest
    ): Call<NewBookmarkResponse>

    @PATCH("users/save-post")
    fun patchUnBookmark(
        @Body request: NewBookmarkRequest
    ): Call<NewBookmarkResponse>

    //팔로우 팔로잉 목록 가져오기
    @GET("users/profiles/follower-list?")
    fun getFollower(
        @Query("requestUserIdx") requestUserIdx: Int,
        @Query("targetUserIdx") targetUserIdx: Int
        ): Call<GetFollowerResponse>

    @GET("users/profiles/following-list?")
    fun getFollowing(
        @Query("requestUserIdx") requestUserIdx: Int,
        @Query("targetUserIdx") targetUserIdx: Int
    ): Call<GetFollowingResponse>

    //댓글 작성
    @POST("posts/comments")
    fun postComment(
        @Body request: NewCommentRequest
    ): Call<NewCommentResponse>
    //댓글 조회
    @GET("posts/comments?")
    fun getComment(
        @Query("postIdx") postIdx: Int,
        @Query("userIdx") userIdx: Int
    ):Call<GetCommentResponse>

    //대댓글 작성
    @POST("posts/comments-replies")
    fun postReply(
        @Body request: NewReplyRequest
    ): Call<NewReplyResponse>

    //대댓글 조회
    @GET("posts/comments-replies?")
    fun getReply(
        @Query("postIdx") postIdx: Int,
        @Query("commentIdx") commentIdx: Int,
        @Query("userIdx") userIdx: Int
    ):Call<GetReplyResponse>


}

