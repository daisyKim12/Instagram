package com.project.Instargram.kotlin.src.main

import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
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
    @GET("users/profile/{userIdx}")
    fun getProfile(
        @Path("userIdx") userIdx: Int
    ): Call<GetProfileResponse>

    @GET("users/profile/{userIdx}/written-posts")
    fun getUserPost(
        @Path("userIdx") userIdx: Int
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
}

