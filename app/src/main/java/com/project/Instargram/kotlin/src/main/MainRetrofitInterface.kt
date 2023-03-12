package com.project.Instargram.kotlin.src.main

import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MainRetrofitInterface {

    @Multipart
    @POST("posts")
    fun postNewPost(@Part data: MultipartBody.Part, @Part image: MultipartBody.Part)
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



}

