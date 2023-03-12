package com.project.Instargram.kotlin.src.main

import com.google.gson.annotations.SerializedName
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse
import com.project.Instargram.kotlin.src.main.page.model.GetProfileResponse
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
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

    @GET("posts/near-feed/{userIdx}?")
    fun getFeed(
        @Path("userIdx") userIdx: Int,
        @Query("page-num") pageNum: Int
    ): Call<GetPostResponse>

    @GET("users/profile/{userIdx}")
    fun getProfile(
        @Path("userIdx") userIdx: Int
    ): Call<GetProfileResponse>

}

